#!/bin/bash
# Скрипт для Post-Deploy Smoke тестування DSpace екосистеми

# Встановлюємо URL (з .env або дефолтні)
SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)
ENV_FILE="$SCRIPT_DIR/../.env"

if [ -f "$ENV_FILE" ]; then
    # Robust .env parser (ігнорує проблеми з пробілами та лапками)
    while IFS='=' read -r key value; do
        [[ "$key" =~ ^#.*$ ]] && continue
        [[ -z "$key" ]] && continue
        
        # Видаляємо пробіли з ключа та зайві символи/лапки зі значення
        key=$(echo "$key" | tr -d '[:space:]')
        value=$(echo "$value" | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//' -e 's/^"//' -e 's/"$//' -e "s/^'//" -e "s/'$//")
        
        export "$key=$value"
    done < <(grep -vE '^\s*#' "$ENV_FILE" | grep -vE '^\s*$')
fi

UI_URL=${DSPACE_UI_URL:-"https://repo.fby.com.ua"}
API_URL="${DSPACE_SERVER_URL:-"https://repo.fby.com.ua/server"}/api"
OAI_URL="${DSPACE_SERVER_URL:-"https://repo.fby.com.ua/server"}/oai/request?verb=Identify"

echo "🚀 Starting Post-Deploy Smoke Tests..."
FAILED=0

# Функція для перевірки HTTP статусу
check_status() {
    local url=$1
    local expected=$2
    local name=$3

    echo -n "Testing $name ($url) ... "
    # Робимо запит, беремо тільки HTTP код, чекаємо макс 10 секунд
    local status
    status=$(curl -o /dev/null -s -w "%{http_code}\n" -m 10 "$url")

    if [ "$status" == "$expected" ]; then
        echo "✅ OK ($status)"
    else
        echo "❌ FAIL (Expected $expected, got $status)"
        FAILED=1
    fi
}

# 1. Чекаємо трохи, щоб контейнери встигли "прокинутись" після рестарту
echo "⏳ Waiting 15s for Traefik and services to route..."
sleep 15

# 2. Перевірки
check_status "$UI_URL/" "200" "Frontend (Angular)"
check_status "$API_URL" "200" "Backend (REST API)"
check_status "$OAI_URL" "200" "OAI-PMH Endpoint"

# 3. Підсумок
if [ "$FAILED" -eq 0 ]; then
    echo "🎉 All smoke tests passed successfully!"
    exit 0
else
    echo "🛑 Smoke tests failed! Check container logs."
    exit 1
fi