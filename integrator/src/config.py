import os
import logging
import sys

# Намагаємося прочитати змінні з файлу .env (для локального запуску)
try:
    from dotenv import load_dotenv
    load_dotenv(os.path.join(os.path.dirname(__file__), '../../integration.env'))
except ImportError:
    pass

def get_env(key: str, required: bool = True, default: str = None) -> str:
    val = os.getenv(key, default)
    if required and not val:
        print(f"CRITICAL ERROR: Environment variable '{key}' is missing.")
        sys.exit(1)
    return val

def setup_logging():
    logging.basicConfig(
        level=logging.INFO,
        format='%(asctime)s [%(levelname)s] [%(name)s] %(message)s',
        datefmt='%Y-%m-%d %H:%M:%S',
        handlers=[logging.StreamHandler(sys.stdout)]
    )
    logging.getLogger("urllib3").setLevel(logging.WARNING)

# --- КОНФІГУРАЦІЯ ---

# Koha
KOHA_API_URL = get_env("KOHA_API_URL").rstrip('/')
KOHA_USER = get_env("KOHA_API_USER")
KOHA_PASS = get_env("KOHA_API_PASS")

# DSpace
DSPACE_API_URL = get_env("DSPACE_API_URL").rstrip('/')
DSPACE_USER = get_env("DSPACE_API_USER")
DSPACE_PASS = get_env("DSPACE_API_PASS")

# Налаштування DSpace Submission
# Назва секції метаданих у submission-forms.xml (за замовчуванням 'traditionalpageone')
DSPACE_SUBMISSION_SECTION = get_env("DSPACE_SUBMISSION_SECTION", required=False, default="traditionalpageone")

# Налаштування HTTP
TIMEOUT = 15
UPLOAD_TIMEOUT = 120  # Збільшений таймаут для файлів

# --- ПЕРЕВІРКА БЕЗПЕКИ ---
if not KOHA_API_URL.startswith("https://"):
    logging.warning(f"SECURITY WARNING: Koha API URL ({KOHA_API_URL}) uses HTTP. Credentials are sent in cleartext!")
if not DSPACE_API_URL.startswith("https://"):
    logging.warning(f"SECURITY WARNING: DSpace API URL ({DSPACE_API_URL}) uses HTTP. Credentials are sent in cleartext!")