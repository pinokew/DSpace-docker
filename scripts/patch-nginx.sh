#!/bin/bash
set -e

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)
ENV_FILE="$SCRIPT_DIR/../.env"

# --- CONFIGURATION ---
TARGET_FILE="$SCRIPT_DIR/../nginx/conf.d/dspace.conf"
mkdir -p "$(dirname "$TARGET_FILE")"

echo "🔧 Patching Nginx (CLOUDFLARE TUNNEL MODE)..."

cat <<EOF > "$TARGET_FILE"
server {
    # Тунель стукає сюди по HTTP. Жодних SSL тут не треба.
    listen 80;
    server_name _ ;

    client_max_body_size 512M;
    client_header_buffer_size 64k;
    large_client_header_buffers 4 64k;
    
    # Stability
    proxy_connect_timeout 300s;
    proxy_read_timeout 300s;
    proxy_send_timeout 300s;
    proxy_http_version 1.1;

    location / {
        # Sidecar: Angular is local
        proxy_pass http://127.0.0.1:8081;
        
        proxy_set_header Host \$http_host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Host \$http_host;
        
        # Cloudflare Tunnel передає HTTPS ззовні, ми кажемо про це DSpace
        proxy_set_header X-Forwarded-Proto https;
        proxy_set_header X-Forwarded-Port 443;
        
        proxy_cache_bypass \$http_upgrade;
        proxy_set_header Upgrade \$http_upgrade;
    }

    location /server {
        proxy_pass http://dspace:8080/server;
        
        proxy_set_header Host \$http_host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Host \$http_host;
        
        proxy_set_header X-Forwarded-Proto https;
        proxy_set_header X-Forwarded-Port 443;
        
        proxy_cookie_path /server /;
    }
}
EOF

echo "✅ Nginx configured for Cloudflare Tunnel (Simple HTTP)."