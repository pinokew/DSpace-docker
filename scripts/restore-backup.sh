#!/bin/bash

# ==============================================================================
# 🚑 KDV DSpace DISASTER RECOVERY Script
# ==============================================================================
# УВАГА: Цей скрипт повністю видаляє поточні дані та замінює їх даними з архіву!
# Щоб активувати його, вам потрібно відредагувати файл і прибрати коментарі (#).
# ==============================================================================

# 1. Завантаження конфігурації
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

# Завантажуємо .env (безпечно)
if [ -f "$PROJECT_ROOT/.env" ]; then
    export $(grep -v '^#' "$PROJECT_ROOT/.env" | grep -v 'JAVA_OPTS' | grep -v ' ' | xargs)
else
    echo "CRITICAL ERROR: .env file not found."
    exit 1
fi

# 2. Аргументи
BACKUP_FILE_RAW=$1

# Перетворюємо на абсолютний шлях
if [[ "$BACKUP_FILE_RAW" = /* ]]; then
    BACKUP_FILE="$BACKUP_FILE_RAW"
else
    BACKUP_FILE="$(pwd)/$BACKUP_FILE_RAW"
fi

# 3. Перевірки перед стартом
if [ -z "$BACKUP_FILE_RAW" ]; then
    echo "Usage: sudo $0 <path_to_backup_file.tar.gz>"
    exit 1
fi

if [ ! -f "$BACKUP_FILE" ]; then
    echo "ERROR: Backup file not found."
    exit 1
fi

# Тимчасова папка для розпаковки
TEMP_DIR="/tmp/kdv_restore_run_$(date +%s)"

echo "======================================================="
echo "⚠️  WARNING: DSpace DISASTER RECOVERY MODE"
echo "======================================================="
echo "Target Backup: $BACKUP_FILE"
echo ""
echo "This process will:"
echo "  1. STOP all DSpace containers."
echo "  2. DELETE all current Database data."
echo "  3. DELETE all current Assetstore files (PDFs)."
echo "  4. RESTORE data from the backup archive."
echo ""

# --- 🔒 ЗАПОБІЖНИК (SAFETY LOCK) ---
echo "⛔ SCRIPT IS LOCKED FOR SAFETY."
echo "   To enable restore, open this file and comment out the 'exit 0' line below."
echo "   Also uncomment the destructive commands in sections 5 and 6."
exit 0  # <--- ВИДАЛИ АБО ЗАКОМЕНТУЙ ЦЕЙ РЯДОК, ЩОБ СКРИПТ ЗАПРАЦЮВАВ
# -----------------------------------


echo "Are you absolutely sure? Type 'RESTORE' to continue:"
read CONFIRMATION
if [ "$CONFIRMATION" != "RESTORE" ]; then
    echo "Operation cancelled."
    exit 1
fi

# 4. Розпаковка архіву
echo "[1/6] Unpacking backup to temp..."
mkdir -p "$TEMP_DIR"
tar -xzf "$BACKUP_FILE" -C "$TEMP_DIR"

SQL_DUMP=$(find "$TEMP_DIR" -name "*.sql" | head -n 1)
EXTRACTED_ASSETSTORE=$(find "$TEMP_DIR" -type d -name "assetstore" | head -n 1)

if [ -z "$SQL_DUMP" ]; then
    echo "ERROR: No SQL dump found in backup. Aborting."
    rm -rf "$TEMP_DIR"
    exit 1
fi

# 5. Зупинка та Очищення (DESTRUCTIVE STEP)
echo "[2/6] Stopping containers and cleaning volumes..."

# --- РОЗКОМЕНТУЙ НИЖЧЕ ДЛЯ АКТИВАЦІЇ ---
# docker compose -f "$PROJECT_ROOT/docker-compose.yml" down

# echo "   Cleaning PostgreSQL volume..."
# sudo rm -rf "${VOL_POSTGRESQL_PATH:?}/"*

# echo "   Cleaning Solr volume (to force re-index)..."
# sudo rm -rf "${VOL_SOLR_PATH:?}/"*

# if [ -d "$EXTRACTED_ASSETSTORE" ]; then
#     echo "   Cleaning Assetstore volume..."
#     sudo rm -rf "${VOL_ASSETSTORE_PATH:?}/"*
# else
#     echo "   ⚠️  Backup has NO Assetstore (Cloud backup?). SKIPPING Assetstore wipe to save current files."
# fi
# ---------------------------------------

# 6. Відновлення (RESTORE STEP)
echo "[3/6] Restoring Database..."

# --- РОЗКОМЕНТУЙ НИЖЧЕ ДЛЯ АКТИВАЦІЇ ---
# Стартуємо тільки базу
# docker compose -f "$PROJECT_ROOT/docker-compose.yml" up -d dspacedb
# echo "   Waiting for Database to be ready (10s)..."
# sleep 10

# Відновлюємо схему та дані (спочатку дропаємо, бо volume може бути не пустим якщо rm не спрацював)
# docker compose -f "$PROJECT_ROOT/docker-compose.yml" exec -T dspacedb dropdb -U dspace dspace --if-exists
# docker compose -f "$PROJECT_ROOT/docker-compose.yml" exec -T dspacedb createdb -U dspace dspace

# cat "$SQL_DUMP" | docker compose -f "$PROJECT_ROOT/docker-compose.yml" exec -T -i dspacedb psql -U dspace dspace
# ---------------------------------------

# 7. Відновлення файлів
echo "[4/6] Restoring Files..."

# --- РОЗКОМЕНТУЙ НИЖЧЕ ДЛЯ АКТИВАЦІЇ ---
# if [ -d "$EXTRACTED_ASSETSTORE" ]; then
#     echo "   Copying Assetstore files..."
#     cp -r "$EXTRACTED_ASSETSTORE/"* "$VOL_ASSETSTORE_PATH/"
#     # Відновлюємо права (важливо для Docker)
#     sudo chown -R 1000:1000 "$VOL_ASSETSTORE_PATH"
# else
#     echo "   Skipping Assetstore restore (not present in backup)."
# fi
# ---------------------------------------

# 8. Запуск та Індексація
echo "[5/6] Starting Full Stack..."

# --- РОЗКОМЕНТУЙ НИЖЧЕ ДЛЯ АКТИВАЦІЇ ---
# docker compose -f "$PROJECT_ROOT/docker-compose.yml" up -d

# echo "   Waiting for DSpace Backend to start (30s)..."
# sleep 30

# echo "[6/6] Re-indexing Solr (Critical Step)..."
# docker compose -f "$PROJECT_ROOT/docker-compose.yml" exec -T dspace /dspace/bin/dspace index-discovery -b
# ---------------------------------------

# 9. Фінал
rm -rf "$TEMP_DIR"
echo "======================================================="
echo "✅ RESTORE COMPLETED."
echo "   Please allow a few minutes for Solr to rebuild indexes."
echo "======================================================="
```

### Як це працює зараз (Безпечний режим)

Якщо ти запустиш цей скрипт зараз:
```bash
sudo ./scripts/restore-dspace.sh backups/full_local_....tar.gz