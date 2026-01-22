import requests
import logging
from requests.auth import HTTPBasicAuth
from requests.exceptions import RequestException, JSONDecodeError
from .config import KOHA_API_URL, KOHA_USER, KOHA_PASS, TIMEOUT

logger = logging.getLogger("KohaClient")

class KohaClient:
    def __init__(self):
        # 1. Захист від подвійних слешів
        self.base_url = KOHA_API_URL.rstrip('/')
        
        # 2. Попередження про безпеку
        if not self.base_url.startswith("https://"):
            logger.warning(f"⚠️ SECURITY WARNING: Koha API ({self.base_url}) is using HTTP. Basic Auth credentials are visible in local network.")

        # 3. Використання Session (Best Practice)
        self.session = requests.Session()
        self.session.auth = HTTPBasicAuth(KOHA_USER, KOHA_PASS)
        self.session.headers.update({
            "Content-Type": "application/json",
            "Accept": "application/json"
        })

    def _request(self, method: str, path: str, **kwargs):
        """Внутрішній метод із захистом від помилок."""
        url = f"{self.base_url}{path}"
        try:
            response = self.session.request(
                method, 
                url, 
                timeout=TIMEOUT, 
                **kwargs
            )
            return response
        except RequestException as e:
            logger.error(f"❌ Network Error ({method} {path}): {str(e)}")
            return None

    def test_connection(self) -> bool:
        """Перевірка зв'язку."""
        logger.info(f"Connecting to Koha API at {self.base_url}...")
        
        response = self._request("GET", "/api/v1/libraries")
        
        if response is not None:
            # 4. Безпечна обробка JSON
            try:
                if response.status_code == 200:
                    libs = response.json()
                    logger.info(f"✅ Koha Connection SUCCESS! Found {len(libs)} libraries.")
                    return True
                
                # Обробка помилок
                elif response.status_code == 401:
                    logger.error("❌ Koha Auth Failed (401). Check .env credentials.")
                elif response.status_code == 403:
                    logger.error("❌ Koha Forbidden (403). Check permissions.")
                elif response.status_code == 404:
                    logger.error(f"❌ Koha Endpoint Not Found (404): {response.url}")
                else:
                    logger.error(f"❌ Koha Error {response.status_code}")
                    logger.debug(f"Response body: {response.text[:200]}") # Логуємо тільки початок
                    
            except JSONDecodeError:
                logger.error(f"❌ Invalid JSON from Koha (Code {response.status_code})")
                logger.debug(f"Raw response: {response.text[:200]}")

        return False

    def get_biblio(self, biblio_id: int):
        """Отримує книгу за ID з обробкою помилок."""
        response = self._request("GET", f"/api/v1/biblios/{biblio_id}")
        
        if response:
            if response.status_code == 200:
                try:
                    return response.json()
                except JSONDecodeError:
                    logger.error(f"❌ Failed to parse JSON for biblio {biblio_id}")
            elif response.status_code == 404:
                logger.warning(f"Biblio {biblio_id} not found in Koha.")
            else:
                logger.error(f"Error fetching biblio {biblio_id}: {response.status_code}")
        
        return None