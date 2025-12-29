#!/bin/bash

# Отримуємо назву контейнера (або дефолт dspace)
CONTAINER_NAME="dspace"

echo "[$(date)] --- Starting DSpace Maintenance ---"

# 1. Витягуємо текст з нових файлів (Filter Media)
# -v: verbose (детально)
# -m: max 1000 items (щоб не вішати сервер, якщо файлів дуже багато)
echo "[$(date)] Running Filter Media..."
docker exec "$CONTAINER_NAME" /dspace/bin/dspace filter-media -v

# 2. Оновлюємо пошуковий індекс (Discovery)
# -b: build index (оптимізує індекс)
echo "[$(date)] Running Index Discovery..."
docker exec "$CONTAINER_NAME" /dspace/bin/dspace index-discovery -b

echo "[$(date)] --- Maintenance Complete ---"

sudo poweroff

# 1.  Відкрий редактор cron на хості:
#     ```bash
#     crontab -e
#         *(Якщо спитає редактор, обирай `nano` — він найпростіший).*

# 2.  Прокрути в самий низ і додай такий рядок (заміни `/home/user/...` на реальний шлях до твого проекту):

#     ```bash
#     # Запускати індексацію DSpace щодня о 13:00
#     0 13 * * * /home/user/шлях/до/папки/scripts/run-maintenance.sh >> /home/user/шлях/до/папки/cron.log 2>&1
    
#     *Пояснення:*
#     * `0 13 * * *` — 0 хвилин, 13 годин, кожен день, кожен місяць.
#     * `>> .../cron.log` — записувати результат у файл, щоб ти міг перевірити, чи воно працювало.