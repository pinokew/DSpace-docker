import sys
import os
import logging
from src.koha import KohaClient
from src.dspace import DSpaceClient

# --- НАЛАШТУВАННЯ ---
# ID книги, яку ми точно знаємо (з твого посилання)
TEST_BIBLIO_ID = 14

def main():
    # --- ВИПРАВЛЕННЯ ЛОГУВАННЯ ---
    # Примусово (force=True) переналаштовуємо логування на рівень INFO,
    # щоб перебити дефолтні налаштування, які створив import config.
    logging.basicConfig(
        level=logging.INFO,
        format='%(asctime)s [%(levelname)s] [%(name)s] %(message)s',
        datefmt='%Y-%m-%d %H:%M:%S',
        force=True,
        handlers=[logging.StreamHandler(sys.stdout)]
    )
    
    logger = logging.getLogger("SmokeTest")
    
    logger.info("========================================")
    logger.info("   KDV INTEGRATOR: DATA FETCH TEST")
    logger.info(f"   Target Book ID: {TEST_BIBLIO_ID}")
    logger.info("========================================")
    
    # 1. Koha Test
    logger.info("\n[1] Connecting to Koha...")
    koha = KohaClient()
    
    if koha.test_connection():
        logger.info(f"Attempting to fetch details for Biblio ID {TEST_BIBLIO_ID}...")
        book = koha.get_biblio(TEST_BIBLIO_ID)
        
        if book:
            # Спробуємо дістати заголовок
            title = book.get("title", "No Title")
            author = book.get("author", "No Author")
            
            # Для діагностики виведемо сирі дані, якщо книга знайдена, але поля пусті
            logger.info(f"\n📚 SUCCESS! FOUND BOOK:")
            logger.info(f"   ID:     {TEST_BIBLIO_ID}")
            logger.info(f"   Title:  {title}")
            logger.info(f"   Author: {author}")
            
            # (Опційно) Перевірка наших кастомних полів 956
            # Koha API може повертати MARC поля у структурі 'metadata'
            if 'metadata' in book:
                 logger.info("   (Record contains MARC metadata blob)")
        else:
            logger.warning(f"\n⚠️  BOOK NOT FOUND (ID {TEST_BIBLIO_ID})")
            logger.warning("   Please check if this 'biblionumber' exists in Koha.")
            logger.warning("   (Open Koha -> Search -> Click a book -> Look at URL for 'biblionumber=...')")
    else:
        logger.error("❌ Koha Connection Failed. Skipping book fetch.")

    # 2. DSpace Test
    logger.info("\n[2] Connecting to DSpace...")
    dspace = DSpaceClient()
    if dspace.login():
        dspace.check_status()
    else:
        logger.error("❌ DSpace Login Failed.")

if __name__ == "__main__":
    main()