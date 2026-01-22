import os
import requests
import logging
from json import JSONDecodeError
from requests.exceptions import RequestException
from .config import (
    DSPACE_API_URL, DSPACE_USER, DSPACE_PASS, 
    TIMEOUT, UPLOAD_TIMEOUT, DSPACE_SUBMISSION_SECTION
)

logger = logging.getLogger("DSpaceClient")

class DSpaceClient:
    def __init__(self):
        # 1. URL Safety & Security Check
        self.base_url = DSPACE_API_URL.rstrip('/')
        if not self.base_url.startswith("https://"):
            logger.warning(f"⚠️ DSpace API ({self.base_url}) is using HTTP. Token leakage risk.")

        self.session = requests.Session()
        self.session.headers.update({
            "Accept": "application/json"
        })
        self.token = None

    def _update_xsrf_header(self):
        """Оновлює заголовок X-XSRF-TOKEN з куків."""
        csrf_cookie = self.session.cookies.get("DSPACE-XSRF-COOKIE")
        if csrf_cookie:
            self.session.headers.update({"X-XSRF-TOKEN": csrf_cookie})
            logger.debug("Refreshed XSRF Header")

    def login(self) -> bool:
        """
        Виконує процедуру входу.
        Це єдиний метод, який не використовує _request (щоб уникнути рекурсії).
        """
        # Крок 0: Отримати початковий XSRF
        try:
            self.session.get(f"{self.base_url}/authn/status", timeout=TIMEOUT)
            self._update_xsrf_header()
        except RequestException as e:
            logger.error(f"Failed to reach DSpace for login prep: {e}")
            return False

        headers_login = {"Content-Type": "application/x-www-form-urlencoded"}
        payload = {"user": DSPACE_USER, "password": DSPACE_PASS}

        logger.info(f"Authenticating as {DSPACE_USER}...")
        
        try:
            response = self.session.post(
                f"{self.base_url}/authn/login",
                data=payload,
                headers=headers_login,
                timeout=TIMEOUT
            )

            if response.status_code in [200, 204]:
                bearer = response.headers.get("Authorization")
                if bearer:
                    self.token = bearer
                    self.session.headers.update({
                        "Authorization": bearer,
                        "Content-Type": "application/json"
                    })
                    self._update_xsrf_header()
                    logger.info("✅ DSpace Login SUCCESS")
                    return True
            
            logger.error(f"❌ Login Failed. Code: {response.status_code}")
            return False
        except RequestException as e:
            logger.error(f"❌ Network Error during login: {e}")
            return False

    def _request(self, method: str, endpoint: str, **kwargs):
        """
        Універсальна обгортка для запитів.
        - Автоматичний логін, якщо немає токена.
        - Автоматичний ре-логін при 401.
        - Оновлення XSRF.
        - Обробка помилок мережі.
        """
        # 1. Auto-login check
        if not self.token and endpoint != "/authn/login":
            logger.info("No active session. Logging in first...")
            if not self.login():
                return None

        url = f"{self.base_url}{endpoint}"
        current_timeout = kwargs.pop('timeout', TIMEOUT)

        try:
            response = self.session.request(method, url, timeout=current_timeout, **kwargs)
            
            # Оновлюємо XSRF, якщо сервер надіслав нову куку
            self._update_xsrf_header()

            # 2. Handle Token Expiration (401)
            if response.status_code == 401:
                logger.warning("Session expired (401). Re-authenticating...")
                if self.login():
                    # Retry request
                    response = self.session.request(method, url, timeout=current_timeout, **kwargs)
                else:
                    return None # Login failed again

            return response

        except RequestException as e:
            logger.error(f"❌ Request Error ({method} {endpoint}): {e}")
            return None

    def get_collections(self):
        """Отримує список колекцій."""
        response = self._request("GET", "/core/collections?size=5")
        
        if response and response.status_code == 200:
            try:
                return response.json().get('_embedded', {}).get('collections', [])
            except JSONDecodeError:
                logger.error("Invalid JSON from collections endpoint")
        else:
            code = response.status_code if response else "No Response"
            logger.error(f"Failed to fetch collections. Code: {code}")
        
        return []

    def create_workspace_item(self, collection_uuid):
        """Створює чернетку."""
        params = {"collection": collection_uuid}
        # Передаємо json={} для правильного Content-Type
        response = self._request("POST", "/submission/workspaceitems", params=params, json={})
        
        if response and response.status_code == 201:
            try:
                item = response.json()
                wsi_id = item['id']
                # Safe extraction
                item_uuid = item.get('_embedded', {}).get('item', {}).get('uuid', 'Unknown')
                logger.info(f"✅ WorkspaceItem created! ID: {wsi_id}, UUID: {item_uuid}")
                return item
            except JSONDecodeError:
                logger.error("Failed to parse created item JSON")
        else:
            self._log_error("Create Item", response)
        
        return None

    def add_metadata(self, wsi_id, metadata_list):
        """
        Додає метадані.
        Використовує DSPACE_SUBMISSION_SECTION з конфігу.
        """
        operations = []
        for meta in metadata_list:
            # Валідація вхідних даних
            if "key" not in meta or "value" not in meta:
                logger.error(f"Invalid metadata entry: {meta}")
                continue

            operations.append({
                "op": "add",
                "path": f"/sections/{DSPACE_SUBMISSION_SECTION}/{meta['key']}",
                "value": [{"value": meta['value']}]
            })
        
        if not operations:
            logger.warning("No valid metadata to add.")
            return False

        logger.info(f"Adding {len(operations)} metadata fields to Item {wsi_id}...")
        
        # PATCH вимагає спец. заголовок
        headers = {"Content-Type": "application/json-patch+json"}
        
        response = self._request("PATCH", f"/submission/workspaceitems/{wsi_id}", 
                                 json=operations, headers=headers)
        
        if response and response.status_code == 200:
            logger.info("✅ Metadata added successfully!")
            return True
        
        self._log_error("Add Metadata", response)
        return False

    def upload_bitstream(self, wsi_id, file_path):
        """
        Завантажує файл. Використовує UPLOAD_TIMEOUT.
        """
        if not os.path.exists(file_path):
            logger.error(f"File not found: {file_path}")
            return False

        file_name = os.path.basename(file_path)
        logger.info(f"Uploading file '{file_name}' to Item {wsi_id}...")

        # Тимчасово прибираємо Content-Type, щоб requests сформував multipart
        original_ct = self.session.headers.pop("Content-Type", None)
        
        try:
            with open(file_path, 'rb') as f:
                files = {'file': (file_name, f, 'application/pdf')}
                
                # Використовуємо _request, але передаємо файли
                response = self._request("POST", f"/submission/workspaceitems/{wsi_id}", 
                                         files=files, timeout=UPLOAD_TIMEOUT)
                
                # 200 = Updated (file added), 201 = Created
                if response and response.status_code in [200, 201]:
                    logger.info("✅ File Uploaded Successfully!")
                    return True
                
                self._log_error("Upload File", response)
                return False
        except Exception as e:
            logger.error(f"❌ File Error: {e}")
            return False
        finally:
            if original_ct:
                self.session.headers["Content-Type"] = original_ct

    def grant_license(self, wsi_id):
        """Grant License."""
        operations = [{
            "op": "add",
            "path": "/sections/license/granted",
            "value": True
        }]
        headers = {"Content-Type": "application/json-patch+json"}
        
        response = self._request("PATCH", f"/submission/workspaceitems/{wsi_id}", 
                                 json=operations, headers=headers)
        
        if response and response.status_code == 200:
            logger.info("✅ License granted!")
            return True
        
        self._log_error("Grant License", response)
        return False

    def publish_item(self, wsi_id):
        """Deposit Item."""
        wsi_uri = f"{self.base_url}/submission/workspaceitems/{wsi_id}"
        headers = {"Content-Type": "text/uri-list"}
        
        logger.info(f"Publishing (Depositing) WorkspaceItem {wsi_id}...")
        
        response = self._request("POST", "/workflow/workflowitems", 
                                 data=wsi_uri, headers=headers)
        
        # 200/201 - OK with body, 204 - OK no content
        if response and response.status_code in [200, 201, 204]:
            logger.info("✅ Item Deposited!")
            return True
        
        self._log_error("Publish Item", response)
        return False

    def _log_error(self, context, response):
        """Допоміжний метод для детального логування помилок."""
        if not response:
            return
        
        logger.error(f"❌ {context} Failed. Code: {response.status_code}")
        try:
            # Намагаємось вивести гарний JSON
            logger.error(f"Details: {response.json()}")
        except JSONDecodeError:
            # Якщо це HTML або текст
            preview = response.text[:500] if response.text else "Empty response"
            logger.debug(f"Raw response: {preview}")