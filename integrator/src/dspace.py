import requests
import logging
from requests.exceptions import RequestException
from .config import DSPACE_API_URL, DSPACE_USER, DSPACE_PASS, TIMEOUT

logger = logging.getLogger("DSpaceClient")

class DSpaceClient:
    def __init__(self):
        self.base_url = DSPACE_API_URL
        self.session = requests.Session()
        # Стандартні заголовки
        self.session.headers.update({
            "Content-Type": "application/x-www-form-urlencoded", # Важливо для логіну!
            "Accept": "application/json"
        })
        self.token = None

    def _fetch_xsrf_token(self):
        """
        Робить 'холостий' запит, щоб отримати DSPACE-XSRF-COOKIE.
        DSpace вимагає передавати цей токен у заголовку X-XSRF-TOKEN для всіх POST запитів.
        """
        try:
            # Запитуємо статус або просто корінь API
            response = self.session.get(f"{self.base_url}/authn/status", timeout=TIMEOUT)
            
            # Витягуємо токен з куків
            xsrf_token = response.cookies.get("DSPACE-XSRF-COOKIE")
            if xsrf_token:
                self.session.headers.update({"X-XSRF-TOKEN": xsrf_token})
                logger.debug("Captured XSRF Token.")
                return True
            else:
                logger.warning("No DSPACE-XSRF-COOKIE found in response.")
                return False
        except RequestException as e:
            logger.error(f"Failed to fetch XSRF token: {e}")
            return False

    def login(self) -> bool:
        """
        Автентифікація в DSpace REST API.
        """
        logger.info(f"Attempting login to {self.base_url} as {DSPACE_USER}...")

        # Крок 1: Отримати CSRF токен
        self._fetch_xsrf_token()

        # Крок 2: Відправити креденшли
        auth_url = f"{self.base_url}/authn/login"
        
        try:
            # DSpace очікує дані як form-data (не JSON!) для endpoint /login
            payload = {"user": DSPACE_USER, "password": DSPACE_PASS}
            
            response = self.session.post(auth_url, data=payload, timeout=TIMEOUT)
            
            # Успішний логін може бути 200 (OK) або 204 (No Content)
            if response.status_code in [200, 204]:
                # Перевіряємо заголовок Authorization
                bearer = response.headers.get("Authorization")
                if bearer:
                    self.token = bearer
                    # Оновлюємо заголовки сесії для майбутніх запитів (JSON)
                    self.session.headers.update({
                        "Authorization": bearer,
                        "Content-Type": "application/json" # Повертаємо JSON для інших запитів
                    })
                    logger.info("✅ Login Successful (Bearer token received)")
                    return True
                else:
                    logger.error("❌ Login returned 200/204 but NO Authorization header found.")
                    return False
            elif response.status_code == 401:
                logger.error("❌ Login Failed: 401 Unauthorized (Check password)")
                return False
            elif response.status_code == 403:
                logger.error("❌ Login Failed: 403 Forbidden (Check XSRF token or IP restrictions)")
                return False
            else:
                logger.error(f"❌ Login Failed. Code: {response.status_code}. Response: {response.text}")
                return False

        except RequestException as e:
            logger.error(f"❌ Network Error during login: {str(e)}")
            return False

    def check_status(self) -> bool:
        """Перевіряє, чи ми дійсно залогінені."""
        if not self.token:
            return False
            
        try:
            response = self.session.get(f"{self.base_url}/authn/status", timeout=TIMEOUT)
            if response.status_code == 200:
                data = response.json()
                if data.get("authenticated", False):
                    logger.info(f"✅ Status Verified: Authenticated as {data.get('authenticationMethod')}")
                    return True
            
            logger.warning(f"❌ Status Check Failed. Code: {response.status_code}")
            return False
        except RequestException:
            return False