#!/bin/bash
set -e

# --- 1. Load .env ---
SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)
ENV_FILE="$SCRIPT_DIR/../.env"

if [ -f "$ENV_FILE" ]; then
    set -a
    source <(grep -vE '^\s*#' "$ENV_FILE" | grep -vE '^\s*$')
    set +a
else
    echo "❌ Error: .env file not found."
    exit 1
fi

TARGET_FILE="$SCRIPT_DIR/../ui-config/config.yml"
mkdir -p "$(dirname "$TARGET_FILE")"

echo "🔧 Patching Frontend (config.yml)..."

# --- 2. Generate YAML ---
# Ми використовуємо cat <<EOF, щоб створити чистий файл з нуля
# Значення беруться прямо з .env

cat <<EOF > "$TARGET_FILE"
ui:
  ssl: ${DSPACE_UI_SSL:-false}
  host: ${DSPACE_UI_HOST:-0.0.0.0}
  port: ${DSPACE_UI_PORT:-8081}
  nameSpace: ${DSPACE_UI_NAMESPACE:-/}
  baseUrl: ${DSPACE_UI_BASEURL}
  useProxies: true

rest:
  ssl: ${DSPACE_REST_SSL:-false}
  host: ${DSPACE_REST_HOST:-localhost}
  port: ${DSPACE_UI_PORT:-8081}
  nameSpace: ${DSPACE_REST_NAMESPACE:-/server}

themes:
  - name: dspace
    headTags:
      - tagName: link
        attributes:
          rel: icon
          href: assets/dspace/images/favicons/favicon.ico
          sizes: any
      - tagName: link
        attributes:
          rel: icon
          href: assets/dspace/images/favicons/favicon.svg
          type: image/svg+xml
      - tagName: link
        attributes:
          rel: manifest
          href: assets/dspace/images/favicons/manifest.webmanifest
EOF

echo "✅ Frontend configured."