#!/bin/bash
set -e

# Шляхи
CONFIG_DIR="dspace/config"
SOURCE_FILE="$CONFIG_DIR/local.cfg.EXAMPLE"
TARGET_FILE="$CONFIG_DIR/local.cfg"

echo "==> DSpace Configuration Patcher"

# 1. Створюємо папку, якщо треба
mkdir -p "$CONFIG_DIR"

# 2. Витягуємо свіжий приклад з контейнера
echo "📥 Extracting local.cfg.EXAMPLE from container..."
docker cp dspace:/dspace/config/local.cfg.EXAMPLE "$CONFIG_DIR/"

# 3. Копіюємо приклад у робочий файл (перезаписуємо старий)
cp "$SOURCE_FILE" "$TARGET_FILE"
echo "✅ Base file created from EXAMPLE."

# --- ФУНКЦІЯ ДЛЯ ПАТЧИНГУ ---
set_config() {
    local key="$1"
    local value="$2"
    
    # Екрануємо спецсимволи для sed (особливо слеші / та амперсанди &)
    # Використовуємо | як роздільник у sed, тому екрануємо | у значенні
    local escaped_value
    escaped_value=$(echo "$value" | sed 's/|/\\|/g')

    # Перевіряємо, чи ключ існує (закоментований або ні)
    if grep -q "^\s*#\?\s*${key}\s*=" "$TARGET_FILE"; then
        # Якщо існує: Знаходимо рядок, прибираємо коментар, замінюємо значення
        # s|regex|replacement|
        # ^\s*#\?\s*${key}\s*=.* -> шукає початок рядка, можливі пробіли, можливий #, ключ, =, решта рядка
        sed -i "s|^\s*#\?\s*${key}\s*=.*|${key} = ${escaped_value}|" "$TARGET_FILE"
        echo "   Updated: $key"
    else
        # Якщо не існує: Додаємо в кінець файлу
        echo "${key} = ${value}" >> "$TARGET_FILE"
        echo "   Added:   $key"
    fi
}

echo "✍️  Applying KDV Production settings..."

# =================================================================
# НАЛАШТУВАННЯ (ЗМІНЮЙ ТУТ)
# =================================================================

# 1. URLS (Nginx 8081)
set_config "dspace.dir" "/dspace"
set_config "dspace.name" "KDV Library Repository"
set_config "dspace.ui.url" "http://localhost:8081"
set_config "dspace.server.url" "http://localhost:8081/server"

# 2. SSR (Internal)
set_config "dspace.server.ssr.url" "http://dspace:8080/server"

# 3. DATABASE
set_config "db.url" "jdbc:postgresql://dspacedb:5432/dspace"
set_config "db.username" "dspace"
set_config "db.password" "dspace"

# 4. SOLR
set_config "solr.server" "http://dspacesolr:8983/solr"

# 5. PROXY TRUST (Для білого екрану)
set_config "proxies.trusted.ipranges" "0.0.0.0/0"

# 6. CORS (Для логіну)
# Тут ми використовуємо змінну ${dspace.ui.url}, яку DSpace підставить сам
set_config "rest.cors.allowed-origins" "\${dspace.ui.url}"

# 7. UPLOAD LIMITS
set_config "spring.servlet.multipart.max-file-size" "512MB"
set_config "spring.servlet.multipart.max-request-size" "512MB"

# =================================================================

# 4. Виставляємо права
echo "🔒 Setting permissions (Owner: 1000:1000)..."
sudo chown 1000:1000 "$TARGET_FILE"
sudo chmod 644 "$TARGET_FILE"

echo "✅ Patching complete!"
echo "👉 Restart DSpace to apply: docker compose -f docker-compose.yml -f docker-compose.prod.yml restart dspace"