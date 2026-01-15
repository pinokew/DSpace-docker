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

echo "🔧 Patching Backend Configuration..."

set_config() {
    local key="$1"
    local value="$2"
    local file="$3"
    local escaped_value=$(echo "$value" | sed 's/|/\\|/g')
    if grep -q "^$key =" "$file"; then
        sed -i "/^$key =/d" "$file"
    fi
    echo "$key = $value" >> "$file"
    echo "   Set: $key"
}

remove_config() {
    local key="$1"
    local file="$2"
    if grep -q "^$key =" "$file"; then
        sed -i "/^$key =/d" "$file"
        echo "   REMOVED: $key"
    fi
}

# --- 2. CLEANUP ---
echo "🧹 Cleaning up..."
remove_config "authentication-oidc.authorize-url" "$TARGET_FILE"
remove_config "authentication-oidc.token-url" "$TARGET_FILE"
remove_config "authentication-oidc.user-info-url" "$TARGET_FILE"
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

# --- 6. PROXY ---
set_config "useProxies" "true" "$TARGET_FILE"
set_config "proxies.trusted.ipranges" "${DSPACENET_SUBNET:-172.23.0.0/16}" "$TARGET_FILE"

# --- 7. CORS ---
set_config "rest.cors.allowed-origins" "${DSPACE_UI_BASEURL}, http://localhost:8081, http://dspace-angular:80" "$TARGET_FILE"
set_config "rest.cors.allowed-methods" "GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD" "$TARGET_FILE"

# --- 8. UPLOAD ---
set_config "spring.servlet.multipart.max-file-size" "512MB" "$TARGET_FILE"
set_config "spring.servlet.multipart.max-request-size" "512MB" "$TARGET_FILE"
set_config "webui.content_disposition_threshold" "8589934592" "$TARGET_FILE"

# --- 9. OIDC AUTHENTICATION ---
if [ "$OIDC_ENABLED" = "true" ]; then
    echo "🔐 Configuring OIDC (Microsoft Entra ID)..."
    
    set_config "plugin.sequence.org.dspace.authenticate.AuthenticationMethod" \
        "org.dspace.authenticate.OidcAuthentication, org.dspace.authenticate.PasswordAuthentication" \
        "$TARGET_FILE"

    set_config "authentication-oidc.client-id" "${OIDC_CLIENT_ID}" "$TARGET_FILE"
    set_config "authentication-oidc.client-secret" "${OIDC_CLIENT_SECRET}" "$TARGET_FILE"
    
    MS_AUTHORIZE="https://login.microsoftonline.com/${OIDC_TENANT_ID}/oauth2/v2.0/authorize"
    MS_TOKEN="https://login.microsoftonline.com/${OIDC_TENANT_ID}/oauth2/v2.0/token"
    MS_USERINFO="https://graph.microsoft.com/oidc/userinfo"
    MS_ISSUER="https://login.microsoftonline.com/${OIDC_TENANT_ID}/v2.0"
    
    set_config "authentication-oidc.authorize-endpoint" "$MS_AUTHORIZE" "$TARGET_FILE"
    set_config "authentication-oidc.token-endpoint" "$MS_TOKEN" "$TARGET_FILE"
    set_config "authentication-oidc.user-info-endpoint" "$MS_USERINFO" "$TARGET_FILE"
    set_config "authentication-oidc.issuer" "$MS_ISSUER" "$TARGET_FILE"
    set_config "authentication-oidc.redirect-url" "${DSPACE_REST_BASEURL}/api/authn/oidc" "$TARGET_FILE"
    
    set_config "authentication-oidc.can-self-register" "${OIDC_CAN_SELF_REGISTER:-true}" "$TARGET_FILE"
    set_config "authentication-oidc.scopes" "openid,email,profile" "$TARGET_FILE"
    set_config "authentication-oidc.user-email-attribute" "email" "$TARGET_FILE" 
    
    if [ -n "$OIDC_EMAIL_DOMAIN" ]; then
        set_config "authentication-oidc.domain" "$OIDC_EMAIL_DOMAIN" "$TARGET_FILE"
    fi
else
    echo "ℹ️ OIDC is disabled."
    set_config "plugin.sequence.org.dspace.authenticate.AuthenticationMethod" \
        "org.dspace.authenticate.PasswordAuthentication" \
        "$TARGET_FILE"
fi

# --- 10. UX TWEAKS ---
echo "✨ Applying UX improvements..."
set_config "eperson.registration.require-terms" "false" "$TARGET_FILE"

# --- 11. REQUEST COPY (SMART MODE) ---
# logged = форма показується ТІЛЬКИ авторизованим користувачам.
# Анонімів при спробі доступу перекине на сторінку входу.
set_config "request.item.type" "logged" "$TARGET_FILE"
set_config "request.item.helpdesk.override" "false" "$TARGET_FILE"

echo "✅ Configuration updated successfully."