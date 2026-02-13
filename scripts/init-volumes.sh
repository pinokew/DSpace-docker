#!/usr/bin/env bash
# Скрипт для ініціалізації томів (volumes) для DSpace та його компонентів
# Створює необхідні папки та налаштовує права доступу (UID:GID) відповідно до офіційних Docker-образів, щоб уникнути проблем з правами при запуску контейнерів.
set -e

# Базова директорія (зміни, якщо потрібно, або передай як аргумент)
# Використовуємо $HOME поточного користувача
BASE_DIR="$HOME/Dspace/DSpace-volumes"

echo "==> Creating volume directories in: $BASE_DIR"

# 1. Визначаємо шляхи
VOL_PG="$BASE_DIR/postgresql_data"
VOL_SOLR="$BASE_DIR/solr_data"
VOL_ASSET="$BASE_DIR/assetstore"
VOL_EXPORT="$BASE_DIR/exports"
VOL_LOGS="$BASE_DIR/logs"

# 2. Створюємо папки
mkdir -p "$VOL_PG"
mkdir -p "$VOL_SOLR"
mkdir -p "$VOL_ASSET"
mkdir -p "$VOL_EXPORT"
mkdir -p "$VOL_LOGS"

echo "==> Setting permissions..."

# 3. Налаштовуємо права (UID:GID)
# Важливо: Ці ID відповідають користувачам всередині офіційних Docker-образів

# PostgreSQL (зазвичай uid 999 в образі postgres:15)
echo " -> PostgreSQL (999:999)"
sudo chown -R 999:999 "$VOL_PG"
sudo chmod 700 "$VOL_PG"

# Solr (офіційний dspace-solr використовує uid 8983)
echo " -> Solr (8983:8983)"
sudo chown -R 8983:8983 "$VOL_SOLR"
sudo chmod 775 "$VOL_SOLR"

# DSpace (backend/frontend працюють під uid 1000)
echo " -> DSpace Assets/Logs/Exports (1000:1000)"
sudo chown -R 1000:1000 "$VOL_ASSET"
sudo chown -R 1000:1000 "$VOL_EXPORT"
sudo chown -R 1000:1000 "$VOL_LOGS"
sudo chmod 775 "$VOL_ASSET" "$VOL_EXPORT" "$VOL_LOGS"

echo "==> Done! Volumes are ready."
ls -ld "$VOL_PG" "$VOL_SOLR" "$VOL_ASSET"