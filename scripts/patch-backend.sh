#!/bin/bash
set -e

# --- 1. Load .env ---
SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)
ENV_FILE="$SCRIPT_DIR/../.env"

if [ -f "$ENV_FILE" ]; then
    echo "🌍 Loading environment variables..."
    set -a
    source <(grep -vE '^\s*#' "$ENV_FILE" | grep -vE '^\s*$')
    set +a
else
    echo "❌ Error: .env file not found."
    exit 1
fi

TARGET_FILE="dspace/config/local.cfg"

echo "🔧 Patching Backend Configuration (ROLLBACK & FIX)..."

# Функція: видаляє ключ, якщо він є, і додає новий
set_config() {
    local key="$1"
    local value="$2"
    local file="$3"
    
    local escaped_value=$(echo "$value" | sed 's/|/\\|/g')
    
    # Видаляємо старий рядок
    if grep -q "^$key =" "$file"; then
        sed -i "/^$key =/d" "$file"
    fi
    
    # Додаємо новий
    echo "$key = $value" >> "$file"
    echo "   Set: $key"
}

# Функція: ПОВНІСТЮ ВИДАЛЯЄ ключ (для очищення помилкових налаштувань)
remove_config() {
    local key="$1"
    local file="$2"
    if grep -q "^$key =" "$file"; then
        sed -i "/^$key =/d" "$file"
        echo "   REMOVED (Clean-up): $key"
    fi
}

# --- 2. CLEANUP (Видаляємо те, що вбило сервер) ---
echo "🧹 Cleaning up unstable Spring Boot configs..."
remove_config "server.forward-headers-strategy" "$TARGET_FILE"
remove_config "server.tomcat.internal-proxies" "$TARGET_FILE"
remove_config "server.tomcat.remote-ip-header" "$TARGET_FILE"
remove_config "server.tomcat.protocol-header" "$TARGET_FILE"
remove_config "server.tomcat.port-header" "$TARGET_FILE"

# --- 3. BASIC URLS ---
set_config "dspace.dir" "${DSPACE_DIR:-/dspace}" "$TARGET_FILE"
set_config "dspace.name" "${DSPACE_NAME:-DSpace Repository}" "$TARGET_FILE"
set_config "dspace.ui.url" "${DSPACE_UI_BASEURL}" "$TARGET_FILE"
set_config "dspace.server.url" "${DSPACE_REST_BASEURL}" "$TARGET_FILE"
set_config "dspace.server.ssr.url" "${DSPACE_REST_SSRBASEURL:-http://dspace:8080/server}" "$TARGET_FILE"

# --- 4. DATABASE ---
DB_URL="jdbc:postgresql://dspacedb:${POSTGRES_INTERNAL_PORT:-5432}/${POSTGRES_DB:-dspace}"
set_config "db.url" "$DB_URL" "$TARGET_FILE"
set_config "db.username" "${POSTGRES_USER:-dspace}" "$TARGET_FILE"
set_config "db.password" "${POSTGRES_PASSWORD:-dspace}" "$TARGET_FILE"

# --- 5. SOLR ---
SOLR_URL="http://dspacesolr:${SOLR_INTERNAL_PORT:-8983}/solr"
set_config "solr.server" "$SOLR_URL" "$TARGET_FILE"

# --- 6. PROXY (SAFE MODE) ---
# DSpace сам вміє працювати з X-Forwarded заголовками, якщо увімкнути useProxies.
# Не треба лізти в налаштування Tomcat через цей файл.
set_config "useProxies" "true" "$TARGET_FILE"
set_config "proxies.trusted.ipranges" "0.0.0.0/0" "$TARGET_FILE"

# --- 7. CORS (MAXIMUM PERMISSION) ---
# Додаємо і HTTP і HTTPS варіанти, щоб точно пустило
set_config "rest.cors.allowed-origins" "${DSPACE_UI_BASEURL}, http://localhost:8081, http://dspace-angular:80" "$TARGET_FILE"

# --- 8. UPLOAD ---
set_config "spring.servlet.multipart.max-file-size" "512MB" "$TARGET_FILE"
set_config "spring.servlet.multipart.max-request-size" "512MB" "$TARGET_FILE"
set_config "webui.content_disposition_threshold" "8589934592" "$TARGET_FILE"

echo "✅ Configuration fixed. Unstable keys removed."