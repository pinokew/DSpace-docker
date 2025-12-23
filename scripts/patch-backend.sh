#!/bin/bash
set -e

# =================================================================
# 1. ЗАВАНТАЖЕННЯ КОНТЕКСТУ (.ENV)
# =================================================================
# Визначаємо, де лежить .env (припускаємо, що скрипт в папці scripts/, а .env в корені)
SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)
ENV_FILE="$SCRIPT_DIR/../.env"

if [ -f "$ENV_FILE" ]; then
    echo "🌍 Loading environment variables from $ENV_FILE..."
    # Експортуємо змінні з .env, ігноруючи коментарі
    set -a
    source <(grep -vE '^\s*#' "$ENV_FILE" | grep -vE '^\s*$')
    set +a
else
    echo "❌ Error: .env file not found at $ENV_FILE"
    exit 1
fi

# =================================================================
# 2. ПІДГОТОВКА ФАЙЛІВ
# =================================================================
CONFIG_DIR="dspace/config"
SOURCE_FILE="$CONFIG_DIR/local.cfg.EXAMPLE"
TARGET_FILE="$CONFIG_DIR/local.cfg"

echo "==> DSpace Configuration Patcher"

# Створюємо папку
mkdir -p "$CONFIG_DIR"

# Витягуємо свіжий приклад з контейнера (якщо контейнер запущений)
# Якщо контейнер не запущений, сподіваємось, що файл вже є, або створюємо пустий
if docker ps | grep -q "${DSPACE_CONTAINER_NAME:-dspace}"; then
    echo "📥 Extracting local.cfg.EXAMPLE from container..."
    docker cp "${DSPACE_CONTAINER_NAME:-dspace}:/dspace/config/local.cfg.EXAMPLE" "$CONFIG_DIR/"
else
    echo "⚠️  Container '${DSPACE_CONTAINER_NAME:-dspace}' not running. Skipping extraction."
    if [ ! -f "$SOURCE_FILE" ]; then
        echo "❌ Source file $SOURCE_FILE not found! Cannot patch."
        exit 1
    fi
fi

# Копіюємо приклад у робочий файл
cp "$SOURCE_FILE" "$TARGET_FILE"
echo "✅ Base file created from EXAMPLE."

# =================================================================
# 3. ФУНКЦІЯ ПАТЧИНГУ
# =================================================================
set_config() {
    local key="$1"
    local value="$2"
    
    # Екрануємо спецсимволи для sed
    local escaped_value
    escaped_value=$(echo "$value" | sed 's/|/\\|/g')

    if grep -q "^\s*#\?\s*${key}\s*=" "$TARGET_FILE"; then
        sed -i "s|^\s*#\?\s*${key}\s*=.*|${key} = ${escaped_value}|" "$TARGET_FILE"
        echo "   Updated: $key"
    else
        echo "${key} = ${value}" >> "$TARGET_FILE"
        echo "   Added:   $key"
    fi
}

echo "✍️  Applying settings from .env..."

# =================================================================
# 4. ЗАСТОСУВАННЯ ЗМІННИХ (БЕЗ ХАРДКОДУ)
# =================================================================

# --- 1. Шляхи та Ім'я ---
set_config "dspace.dir" "${DSPACE_DIR:-/dspace}"
set_config "dspace.name" "${DSPACE_NAME:-DSpace Repository}"

# --- 2. Публічні URL (з .env) ---
# Це те, що ми будемо міняти при переході на Cloudflare
set_config "dspace.ui.url" "${DSPACE_UI_BASEURL}"
set_config "dspace.server.url" "${DSPACE_REST_BASEURL}"

# --- 3. SSR (Внутрішній зв'язок) ---
# Використовуємо внутрішній порт з .env або дефолт 8080
set_config "dspace.server.ssr.url" "${DSPACE_REST_SSRBASEURL:-http://dspace:8080/server}"

# --- 4. DATABASE ---
# Формуємо JDBC URL динамічно
DB_URL="jdbc:postgresql://dspacedb:${POSTGRES_INTERNAL_PORT:-5432}/${POSTGRES_DB:-dspace}"
set_config "db.url" "$DB_URL"
set_config "db.username" "${POSTGRES_USER:-dspace}"
set_config "db.password" "${POSTGRES_PASSWORD:-dspace}"

# --- 5. SOLR ---
SOLR_URL="http://dspacesolr:${SOLR_INTERNAL_PORT:-8983}/solr"
set_config "solr.server" "$SOLR_URL"

# --- 6. PROXY TRUST & SECURITY ---
set_config "proxies.trusted.ipranges" "${PROXIES_TRUSTED_IPRANGES:-0.0.0.0/0}"
# CORS дозволяємо для UI URL (використовуємо внутрішню змінну DSpace config для гнучкості)
set_config "rest.cors.allowed-origins" "\${dspace.ui.url}"

# --- 7. UPLOAD LIMITS ---
# Можна теж винести в ENV, але 512MB поки що ОК як константа або додати в .env
set_config "spring.servlet.multipart.max-file-size" "512MB"
set_config "spring.servlet.multipart.max-request-size" "512MB"

# =================================================================
# 5. ФІНАЛІЗАЦІЯ
# =================================================================
echo "🔒 Setting permissions..."
# Якщо скрипт запущено від root, то 1000:1000 може не спрацювати без sudo, 
# але в контексті docker часто краще лишити як є або chown current user
# sudo chown 1000:1000 "$TARGET_FILE" 2>/dev/null || true
chmod 644 "$TARGET_FILE"

echo "✅ Patching complete!"
echo "👉 Configuration generated for: ${DSPACE_UI_BASEURL}"