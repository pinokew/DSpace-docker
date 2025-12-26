#!/bin/bash
set -e

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)

echo "🚀 Starting KDV DSpace Configuration Setup..."
echo "---------------------------------------------"

# Даємо права на виконання (про всяк випадок)
chmod +x "$SCRIPT_DIR/patch-backend.sh"
chmod +x "$SCRIPT_DIR/patch-frontend.sh"
chmod +x "$SCRIPT_DIR/patch-nginx.sh"
chmod +x "$SCRIPT_DIR/patch-submission-forms.sh"

# Запускаємо скрипти по черзі
"$SCRIPT_DIR/patch-backend.sh"
"$SCRIPT_DIR/patch-frontend.sh"
"$SCRIPT_DIR/patch-nginx.sh"
"$SCRIPT_DIR/patch-submission-forms.sh"

echo "---------------------------------------------"
echo "🎉 All configurations updated from .env!"
echo "👉 Now run: docker compose restart"
