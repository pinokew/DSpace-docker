import os
import sys
import shutil
import logging
import schedule
import time
from datetime import datetime

# Імпорти модулів
sys.path.append(os.path.dirname(os.path.abspath(__file__)))
from config import (
    setup_logging, 
    INTEGRATOR_MOUNT_PATH, 
    FOLDER_INBOX, FOLDER_PROCESSED, FOLDER_ERROR
)
from koha import KohaClient
from dspace import DSpaceClient

# Налаштування логера
setup_logging()
logger = logging.getLogger("Daywalker")

def get_full_path(folder, filename):
    """Будує повний шлях до файлу (враховуючи точку монтування)."""
    # Шлях в Koha (956$u) може бути "Inbox/book.pdf" або просто "book.pdf"
    # Ми припускаємо, що 956$u - це відносний шлях від кореня інтеграції
    
    # Очищаємо шлях від зайвих слешів
    clean_filename = filename.lstrip('/')
    return os.path.join(INTEGRATOR_MOUNT_PATH, clean_filename)

def move_file(src, dest_folder):
    """Переміщує файл у архів (Processed/Error)."""
    try:
        file_name = os.path.basename(src)
        dest_dir = os.path.join(INTEGRATOR_MOUNT_PATH, dest_folder)
        
        if not os.path.exists(dest_dir):
            os.makedirs(dest_dir)
            
        dest_path = os.path.join(dest_dir, file_name)
        
        # Якщо файл вже є в архіві - додаємо timestamp, щоб не затерти
        if os.path.exists(dest_path):
            base, ext = os.path.splitext(file_name)
            ts = int(time.time())
            dest_path = os.path.join(dest_dir, f"{base}_{ts}{ext}")
            
        shutil.move(src, dest_path)
        logger.info(f"📂 Moved file to {dest_folder}: {os.path.basename(dest_path)}")
        return True
    except Exception as e:
        logger.error(f"❌ Failed to move file: {e}")
        return False

def process_single_candidate(biblio, koha, dspace):
    """Обробляє одну книгу."""
    bib_id = biblio['biblioitemnumber'] # або biblionumber
    title = biblio.get('title', 'No Title')
    
    # Дані з парсера 956 (ми їх додали в get_candidates)
    meta = biblio.get('_parsed_956', {})
    file_rel_path = meta.get('u')
    collection_uuid = meta.get('x') # UUID колекції
    
    logger.info(f"--- Processing Biblio {bib_id}: '{title}' ---")
    
    # 1. Перевірка файлу
    if not file_rel_path:
        logger.error("No file path in 956$u")
        return
        
    full_file_path = get_full_path(FOLDER_INBOX, file_rel_path)
    
    # Якщо бібліотекар вказав шлях "book.pdf", шукаємо в Inbox/book.pdf
    # Якщо він вказав "Inbox/book.pdf", то ок. 
    # Спробуємо розумний пошук:
    if not os.path.exists(full_file_path):
        # Спробуємо додати префікс Inbox, якщо його немає
        alt_path = os.path.join(INTEGRATOR_MOUNT_PATH, FOLDER_INBOX, file_rel_path)
        if os.path.exists(alt_path):
            full_file_path = alt_path
        else:
            logger.error(f"❌ File not found on disk: {file_rel_path}")
            # Оновлюємо статус в Koha на Error
            koha.update_biblio_links(bib_id, "", status="error", log_msg="File missing")
            return

    logger.info(f"✅ File found: {full_file_path}")

    # 2. Перевірка колекції
    if not collection_uuid:
        logger.error("❌ No DSpace Collection UUID in 956$x")
        koha.update_biblio_links(bib_id, "", status="error", log_msg="Missing Collection UUID")
        return

    # 3. Створення в DSpace
    # Логінимось
    if not dspace.login():
        logger.error("❌ DSpace Login failed. Skipping.")
        return

    # Create Item
    item = dspace.create_workspace_item(collection_uuid)
    if not item:
        return
    
    wsi_id = item['id']
    
    # Add Metadata (беремо з Koha)
    metadata = [
        {'key': 'dc.title', 'value': title},
        {'key': 'dc.date.issued', 'value': str(datetime.now().year)}, # Поки що поточний рік
        {'key': 'dc.type', 'value': 'Book'},
        {'key': 'local.koha.biblionumber', 'value': str(bib_id)} # Зв'язок
    ]
    # Додатково можна брати автора з Koha (author)
    if 'author' in biblio and biblio['author']:
        metadata.append({'key': 'dc.contributor.author', 'value': biblio['author']})

    dspace.add_metadata(wsi_id, metadata)
    
    # Upload File
    if not dspace.upload_bitstream(wsi_id, full_file_path):
        logger.error("❌ Failed to upload file. Aborting.")
        # Тут можна було б видалити item, але поки залишимо для дебагу
        return

    # Grant License
    dspace.grant_license(wsi_id)
    
    # Publish (Deposit)
    if dspace.publish_item(wsi_id):
        # Успіх!
        # Отримуємо Handle. На жаль, у відповіді publish (200) не завжди є handle одразу (асинхронно).
        # Але ми спробуємо його "вгадати" або отримати item знову.
        # Для простоти поки сформуємо посилання на UUID (або Handle якщо пощастить)
        
        # Зазвичай handle з'являється швидко. Спробуємо запитати item статус.
        # Але поки що запишемо generic link або пустий, головне - статус imported
        
        # TO DO: Реалізувати отримання Handle через GET /core/items/{uuid}
        # Поки пишемо заглушку або шукаємо потім.
        # Для MVP запишемо: "Item created (UUID)"
        
        handle_link = f"http://repo.fby.com.ua/items/{item['_embedded']['item']['uuid']}" # Тимчасовий лінк
        
        logger.info(f"✅ Published! Updating Koha...")
        
        # 4. Оновлення Koha
        if koha.update_biblio_links(bib_id, handle_link, status="imported", log_msg=f"Success {datetime.now()}"):
            # 5. Переміщення файлу
            move_file(full_file_path, FOLDER_PROCESSED)
            logger.info("🎉 Transaction Complete.")
        else:
            logger.error("⚠️ DSpace OK, but Koha Update Failed.")
    else:
        logger.error("❌ Publication failed.")
        # move_file(full_file_path, FOLDER_ERROR) # Можна перемістити в помилкові

def job():
    """Основна робота Daywalker."""
    logger.info("⏰ Starting scheduled scan...")
    
    koha = KohaClient()
    dspace = DSpaceClient()
    
    # 1. Перевірка зв'язку
    if not koha.test_connection():
        logger.error("Koha unavailable. Skipping job.")
        return

    # 2. Пошук кандидатів
    candidates = koha.get_candidates(limit=20)
    
    if not candidates:
        logger.info("No candidates found. Sleeping.")
        return

    # 3. Обробка
    for bib in candidates:
        try:
            process_single_candidate(bib, koha, dspace)
        except Exception as e:
            logger.error(f"CRITICAL ERROR processing biblio: {e}")

def main():
    logger.info("==========================================")
    logger.info("   KDV INTEGRATOR: DAYWALKER (MVP)")
    logger.info("==========================================")
    
    # --- MANUAL RUN (ON DEMAND) ---
    logger.info("🚀 Running ONCE (On Demand Mode)...")
    job()
    logger.info("✅ Done.")
    
    # --- SCHEDULED RUN (PRODUCTION) ---
    # Розкоментуй рядки нижче для продакшн запуску в фоні
    # schedule.every(15).minutes.do(job)
    # 
    # logger.info("⏳ Scheduler started. Press Ctrl+C to exit.")
    # while True:
    #     schedule.run_pending()
    #     time.sleep(1)

if __name__ == "__main__":
    main()