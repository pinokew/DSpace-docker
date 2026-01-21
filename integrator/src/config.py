import os
import logging
import sys

# Намагаємося прочитати змінні з файлу .env (для локального запуску)
try:
    from dotenv import load_dotenv
    # Шукаємо .env на два рівні вище
    load_dotenv(os.path.join(os.path.dirname(__file__), '../../integration.env'))
except ImportError:
    pass  # У Docker змінні вже будуть в environment

def get_env(key: str, required: bool = True, default: str = None) -> str:
    """Отримує змінну оточення з валідацією."""
    val = os.getenv(key, default)
    if required and not val:
        # Тут використовуємо print, бо логер ще може бути не налаштований
        print(f"CRITICAL ERROR: Environment variable '{key}' is missing.")
        sys.exit(1)
    return val

# --- НАЛАШТУВАННЯ ЛОГУВАННЯ ---
def setup_logging():
    """Налаштовує формат логів для всього застосунку."""
    logging.basicConfig(
        level=logging.INFO,
        format='%(asctime)s [%(levelname)s] [%(name)s] %(message)s',
        datefmt='%Y-%m-%d %H:%M:%S',
        handlers=[
            logging.StreamHandler(sys.stdout)
        ]
    )
    # Якщо потрібно, можна знизити рівень шуму від бібліотек
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

# Налаштування HTTP запитів
TIMEOUT = 15  # Секунди

# --- ПЕРЕВІРКА БЕЗПЕКИ ---
if not KOHA_API_URL.startswith("https://"):
    # Поки що просто попередження, щоб не блокувати роботу в локальній мережі
    logging.warning(f"SECURITY WARNING: Koha API URL ({KOHA_API_URL}) uses HTTP. Credentials are sent in cleartext!")