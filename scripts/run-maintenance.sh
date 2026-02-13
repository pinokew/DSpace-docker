#!/bin/bash
# Скрипт для запуску регулярного обслуговування DSpace та безпечного вимкнення.
# Всі логи пишуться в stdout/stderr, які Cron перенаправляє в єдиний лог-файл.
#     crontab -e
#     0 13 * * * /home/user/шлях/до/папки/scripts/run-maintenance.sh >> /home/user/шлях/до/папки/cron.log 2>&1
#     *Пояснення:*
#     * `0 13 * * *` — 0 хвилин, 13 годин, кожен день, кожен місяць.
#     * `>> .../cron.log` — записувати результат у файл, щоб ти міг перевірити, чи воно працювало.

set -e

# Отримуємо назву контейнера
CONTAINER_NAME="dspace"

echo "[$(date)] --- Starting DSpace Maintenance ---"

# 1. Витягуємо текст з нових файлів (Filter Media)
# ПРИБРАНО: прапорець -v (verbose), щоб не засмічувати лог текстом книг.
# -m 1000: обмежує кількість оброблених за раз файлів.
echo "[$(date)] Running Filter Media..."
docker exec "$CONTAINER_NAME" /dspace/bin/dspace filter-media -m 1000

# 2. Оновлюємо пошуковий індекс (Discovery)
# -b: build index (оптимізує індекс)
echo "[$(date)] Running Index Discovery..."
docker exec "$CONTAINER_NAME" /dspace/bin/dspace index-discovery -b

echo "[$(date)] --- Maintenance Complete. Starting Shutdown Sequence ---"

# --- БЛОК БЕЗПЕЧНОГО РОЗМОНТУВАННЯ ---

MOUNT_ROOT="/home/pinokew/GoogleDrive"
SMB_MOUNT="/home/pinokew/Server/Local_SMB"

echo "[$(date)] Unmounting drives..."

# 1. Розмонтування Google Drive
# Використовуємо nullglob, щоб цикл не відпрацював, якщо папка порожня
shopt -s nullglob
for mount_dir in "$MOUNT_ROOT"/*; do
    if mountpoint -q "$mount_dir"; then
        echo "[$(date)] Unmounting: $mount_dir"
        fusermount -uz "$mount_dir"
    fi
done
shopt -u nullglob

# 2. Розмонтування локального SMB (якщо змонтовано)
if mountpoint -q "$SMB_MOUNT"; then
    echo "[$(date)] Unmounting: $SMB_MOUNT"
    fusermount -uz "$SMB_MOUNT"
fi

# 3. Чекаємо завершення запису
sleep 5

# 4. Вбиваємо rclone (щоб не висів процес)
# || true дозволяє скрипту йти далі, навіть якщо rclone не знайдено
killall rclone 2>/dev/null || true

echo "[$(date)] All drives disconnected. System poweroff initiated."

# --- ВИМКНЕННЯ ---
sudo poweroff