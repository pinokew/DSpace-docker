#!/bin/bash
set -e

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

TARGET_FILE="$SCRIPT_DIR/../nginx/conf.d/dspace.conf"
mkdir -p "$(dirname "$TARGET_FILE")"

echo "🔧 Patching Nginx (SIDECAR MODE for Ngrok)..."

# --- CONFIGURATION ---
# УВАГА: Оскільки network_mode: service:dspace-angular,
# Nginx бачить Angular ТІЛЬКИ на localhost!
UPSTREAM_UI_HOST="127.0.0.1"
UPSTREAM_UI_PORT="8081"

# Бекенд знаходиться в іншому контейнері
UPSTREAM_API_HOST="${DSPACE_CONTAINER_NAME:-dspace}"
UPSTREAM_API_PORT="8080"

# Для Ngrok ми завжди вважаємо, що це HTTPS (він термінує SSL)
PROTO_HEADER="https"
PORT_HEADER="443"

cat <<EOF > "$TARGET_FILE"
# --- UPSTREAMS ---
upstream dspace_ui_upstream {
    server ${UPSTREAM_UI_HOST}:${UPSTREAM_UI_PORT};
}

upstream dspace_api_upstream {
    server ${UPSTREAM_API_HOST}:${UPSTREAM_API_PORT};
}

server {
    listen 80;
    server_name _ ;

    client_max_body_size 512M;
    large_client_header_buffers 4 32k;
    
    proxy_connect_timeout 300s;
    proxy_send_timeout 300s;
    proxy_read_timeout 300s;
    
    # Ngrok любить прості з'єднання
    proxy_http_version 1.1;
    proxy_set_header Connection "close";

    location / {
        proxy_pass http://dspace_ui_upstream;
        
        proxy_set_header Upgrade \$http_upgrade;
        proxy_set_header Host \$http_host;
        proxy_cache_bypass \$http_upgrade;
        
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Host \$http_host;
        
        # Force HTTPS for Ngrok
        proxy_set_header X-Forwarded-Proto ${PROTO_HEADER};
        proxy_set_header X-Forwarded-Port ${PORT_HEADER};
    }

    location ${DSPACE_REST_NAMESPACE:-/server} {
        proxy_pass http://dspace_api_upstream${DSPACE_REST_NAMESPACE:-/server};
        
        proxy_set_header Host \$http_host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Host \$http_host;
        
        # Force HTTPS for Ngrok
        proxy_set_header X-Forwarded-Proto ${PROTO_HEADER};
        proxy_set_header X-Forwarded-Port ${PORT_HEADER};
        
        proxy_cookie_path ${DSPACE_REST_NAMESPACE:-/server} /;
    }
}
EOF

echo "✅ Nginx configured for Sidecar + Ngrok"