#!/bin/bash
set -e

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)
ENV_FILE="$SCRIPT_DIR/../.env"

# --- CONFIGURATION ---
TARGET_FILE="$SCRIPT_DIR/../nginx/conf.d/dspace.conf"
SSL_DIR="$SCRIPT_DIR/../nginx/ssl"
mkdir -p "$(dirname "$TARGET_FILE")"
mkdir -p "$SSL_DIR"

echo "🔧 Patching Nginx (CLOUDFLARE ROBUST SSL)..."

# 1. ГЕНЕРАЦІЯ СЕРТИФІКАТІВ (Перезапис для надійності)
echo "🔐 Regenerating Compatible SSL Certificates..."
# Видаляємо старі, щоб не було конфліктів
rm -f "$SSL_DIR/server.crt" "$SSL_DIR/server.key"

# Генеруємо новий RSA ключ (2048 біт) і сертифікат
openssl req -x509 -nodes -days 3650 -newkey rsa:2048 \
    -keyout "$SSL_DIR/server.key" \
    -out "$SSL_DIR/server.crt" \
    -subj "/C=UA/ST=Lviv/L=Lviv/O=FBY/CN=repo.fby.com.ua" 2>/dev/null

# !!! ВИПРАВЛЕННЯ ПРАВ ДОСТУПУ (Щоб Nginx міг читати) !!!
chmod 644 "$SSL_DIR/server.crt"
chmod 644 "$SSL_DIR/server.key"

cat <<EOF > "$TARGET_FILE"
server {
    # 1. SSL CONFIGURATION
    listen 443 ssl;
    server_name _ ;

    ssl_certificate /etc/nginx/ssl/server.crt;
    ssl_certificate_key /etc/nginx/ssl/server.key;
    
    # Стандартні протоколи для сумісності з Cloudflare
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;

    # 2. HTTP FALLBACK
    listen 80;

    # Buffers
    client_max_body_size 512M;
    client_header_buffer_size 64k;
    large_client_header_buffers 4 64k;
    
    # Timeouts
    proxy_connect_timeout 300s;
    proxy_read_timeout 300s;
    proxy_send_timeout 300s;
    
    # Connection handling
    proxy_http_version 1.1;
    proxy_set_header Connection "close";

    location / {
        # Angular Sidecar (Localhost)
        proxy_pass http://127.0.0.1:8081;
        
        proxy_set_header Host \$http_host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Host \$http_host;
        
        # HTTPS Headers
        proxy_set_header X-Forwarded-Proto https;
        proxy_set_header X-Forwarded-Port 443;
    }

    location /server {
        # Backend API Container
        proxy_pass http://dspace:8080/server;
        
        proxy_set_header Host \$http_host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Host \$http_host;
        
        # HTTPS Headers
        proxy_set_header X-Forwarded-Proto https;
        proxy_set_header X-Forwarded-Port 443;
        
        proxy_cookie_path /server /;
    }
}
EOF

echo "✅ Nginx configured: SSL + Permissions Fixed."