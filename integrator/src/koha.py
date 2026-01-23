import requests
import logging
from io import BytesIO
from pymarc import parse_xml_to_array, Record, Field
from requests.auth import HTTPBasicAuth
from requests.exceptions import RequestException, JSONDecodeError
from config import KOHA_API_URL, KOHA_USER, KOHA_PASS, TIMEOUT

logger = logging.getLogger("KohaClient")

class KohaClient:
    def __init__(self):
        self.base_url = KOHA_API_URL.rstrip('/')
        if not self.base_url.startswith("https://"):
            logger.warning(f"⚠️ SECURITY WARNING: Koha API ({self.base_url}) is using HTTP.")

        self.session = requests.Session()
        self.session.auth = HTTPBasicAuth(KOHA_USER, KOHA_PASS)
        self.session.headers.update({
            "Content-Type": "application/json",
            "Accept": "application/json"
        })

    def _request(self, method: str, path: str, **kwargs):
        """Внутрішній метод запиту."""
        url = f"{self.base_url}{path}"
        try:
            response = self.session.request(method, url, timeout=TIMEOUT, **kwargs)
            return response
        except RequestException as e:
            logger.error(f"❌ Network Error ({method} {path}): {str(e)}")
            return None

    def test_connection(self) -> bool:
        """Перевірка зв'язку."""
        response = self._request("GET", "/api/v1/libraries")
        if response and response.status_code == 200:
            logger.info(f"✅ Koha Connection SUCCESS!")
            return True
        return False

    def get_biblio(self, biblio_id: int):
        """Отримує книгу за ID."""
        response = self._request("GET", f"/api/v1/biblios/{biblio_id}")
        if response and response.status_code == 200:
            return response.json()
        return None

    # --- НОВІ МЕТОДИ ДЛЯ ІНТЕГРАТОРА ---

    def get_candidates(self, limit=50):
        """
        Шукає книги, які треба синхронізувати.
        Оскільки Koha API не дає фільтрувати по підполях MARC, 
        ми беремо останні змінені записи і фільтруємо їх тут (Python-side).
        """
        # Сортуємо за часом оновлення (найсвіжіші перші)
        params = {
            "_order_by": "-framework_update",
            "_per_page": limit
        }
        
        logger.info(f"Scanning last {limit} updated records in Koha...")
        response = self._request("GET", "/api/v1/biblios", params=params)
        
        candidates = []
        
        if response and response.status_code == 200:
            try:
                biblios = response.json()
                for bib in biblios:
                    if self._is_candidate(bib):
                        candidates.append(bib)
            except Exception as e:
                logger.error(f"Error parsing candidates: {e}")
        
        logger.info(f"Found {len(candidates)} candidates for processing.")
        return candidates

    def _is_candidate(self, bib_data):
        """
        Перевіряє, чи має книга поле 956$u (файл) і чи вона ще не оброблена.
        """
        if 'metadata' not in bib_data:
            return False
            
        try:
            # Парсимо MARC XML
            record = self._parse_marc(bib_data['metadata'])
            if not record:
                return False

            # Шукаємо поле 956
            field_956 = record['956']
            if not field_956:
                return False
                
            # Перевіряємо $u (шлях до файлу)
            file_path = field_956['u']
            if not file_path:
                return False
                
            # Перевіряємо $y (статус)
            status = field_956['y']
            if status == 'imported':
                return False # Вже зроблено
                
            # Якщо дійшли сюди - це кандидат!
            # Додаємо розпарсені дані в об'єкт для зручності
            bib_data['_parsed_956'] = {
                'u': file_path,
                'x': field_956['x'], # Колекція
                'y': status
            }
            return True
            
        except Exception:
            return False

    def update_biblio_links(self, biblio_id, handle_url, status="imported", log_msg="OK"):
        """
        Оновлює запис у Koha: прописує 856$u (лінк) і 956$y (статус).
        """
        # 1. Отримуємо повний запис
        bib_data = self.get_biblio(biblio_id)
        if not bib_data or 'metadata' not in bib_data:
            logger.error(f"Failed to fetch biblio {biblio_id} for update.")
            return False
            
        # 2. Парсимо MARC
        record = self._parse_marc(bib_data['metadata'])
        if not record:
            return False
            
        # 3. Модифікуємо MARC
        # -- Оновлення 956 (Статус) --
        if '956' in record:
            record['956'].delete_subfield('y')
            record['956'].add_subfield('y', status)
            
            record['956'].delete_subfield('z')
            record['956'].add_subfield('z', log_msg)
        
        # -- Оновлення 856 (Лінк на DSpace) --
        # Видаляємо старі посилання на репозиторій, якщо є
        # (Спрощення: просто додаємо нове поле 856)
        
        # Створюємо нове поле 856
        field_856 = Field(
            tag='856',
            indicators=['4', '0'], # 4=HTTP, 0=Resource
            subfields=[
                'u', handle_url,
                'y', 'Цифровий репозиторій (PDF)'
            ]
        )
        record.add_ordered_field(field_856)
        
        # 4. Серіалізуємо назад в XML
        new_xml = pymarc.record_to_xml(record).decode('utf-8')
        
        # 5. Відправляємо PUT
        payload = bib_data
        payload['metadata'] = new_xml
        
        logger.info(f"Updating Koha Biblio {biblio_id}...")
        response = self._request("PUT", f"/api/v1/biblios/{biblio_id}", json=payload)
        
        if response and response.status_code == 200:
            logger.info(f"✅ Koha Biblio {biblio_id} updated successfully!")
            return True
        else:
            logger.error(f"❌ Failed to update Koha. Code: {response.status_code if response else 'None'}")
            return False

    def _parse_marc(self, xml_string):
        """Допоміжний метод для парсингу XML рядка."""
        try:
            # pymarc очікує байти або файл
            reader = parse_xml_to_array(BytesIO(xml_string.encode('utf-8')))
            return reader[0] if reader else None
        except Exception as e:
            # logger.error(f"MARC parsing error: {e}")
            return None