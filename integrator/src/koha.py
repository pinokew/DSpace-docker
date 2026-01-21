import requests
import logging
from requests.exceptions import RequestException
from .config import KOHA_API_URL, KOHA_USER, KOHA_PASS, TIMEOUT

logger = logging.getLogger("KohaClient")

class KohaClient:
    def __init__(self):
        self.base_url = KOHA_API_URL
        self.session = requests.Session()
        self.session.headers.update({
            "Content-Type": "application/json",
            "Accept": "application/json"
        })
        self._authenticated = False

    def login(self) -> bool:
        """
        Авторизація в Koha через створення сесії (Cookie-based).
        Виправляє помилку 'Basic authentication disabled'.
        """
        endpoint = f"{self.base_url}/api/v1/auth/session"
        payload = {
            "userid": KOHA_USER,
            "password": KOHA_PASS
        }
        
        logger.info(f"Attempting session login to {self.base_url}...")
        
        try:
            response = self.session.post(endpoint, json=payload, timeout=TIMEOUT)
            
            if response.status_code == 201:
                logger.info("✅ Login Successful (Session created)")
                self._authenticated = True
                return True
            else:
                logger.error(f"❌ Login Failed. Code: {response.status_code}. Msg: {response.text}")
                return False
                
        except RequestException as e:
            logger.error(f"❌ Network Error during login: {str(e)}")
            return False

    def _request(self, method: str, path: str, **kwargs):
        """Внутрішній метод для виконання запитів з обробкою помилок."""
        if not self._authenticated:
            logger.warning("Executing request without active session. Trying to login first...")
            if not self.login():
                return None

        url = f"{self.base_url}{path}"
        try:
            response = self.session.request(method, url, timeout=TIMEOUT, **kwargs)
            
            # Якщо сесія протухла (401), пробуємо перелогінитись один раз
            if response.status_code == 401:
                logger.warning("Session expired (401). Re-authenticating...")
                if self.login():
                    # Повторюємо запит
                    response = self.session.request(method, url, timeout=TIMEOUT, **kwargs)
            
            return response
            
        except RequestException as e:
            logger.error(f"❌ Request Error ({method} {path}): {str(e)}")
            return None

    def test_connection(self) -> bool:
        """Перевіряє, чи працює API (отримує список бібліотек)."""
        # Спочатку логінимось
        if not self.login():
            return False

        # Робимо тестовий запит
        response = self._request("GET", "/api/v1/libraries")
        
        if response and response.status_code == 200:
            count = len(response.json())
            logger.info(f"✅ Connection Verified! Found {count} libraries.")
            return True
        elif response:
            logger.error(f"❌ Connection Check Failed. Code: {response.status_code}")
        
        return False

    def get_biblio(self, biblio_id: int):
        """Отримує запис книги за ID."""
        response = self._request("GET", f"/api/v1/biblios/{biblio_id}")
        if response and response.status_code == 200:
            return response.json()
        if response and response.status_code == 404:
            logger.warning(f"Biblio {biblio_id} not found.")
        return None