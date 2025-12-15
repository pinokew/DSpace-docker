#!/usr/bin/env bash
set -euo pipefail

# Load .env if present
if [[ -f ".env" ]]; then
  set -a
  # shellcheck disable=SC1091
  source .env
  set +a
fi

: "${ADMIN_EMAIL:?Set ADMIN_EMAIL in .env}"
: "${ADMIN_PASS:?Set ADMIN_PASS in .env}"
: "${ADMIN_FIRST:?Set ADMIN_FIRST in .env}"
: "${ADMIN_LAST:?Set ADMIN_LAST in .env}"

DCONT=${DCONT:-"dspace"}

echo "==> Checking DSpace backend container exists & is running: $DCONT"
if ! docker ps --format '{{.Names}}' | grep -qx "$DCONT"; then
  echo "❌ Container '$DCONT' is not running."
  echo "   Start it first: docker start $DCONT  (or docker compose up -d dspace)"
  exit 1
fi

echo "==> Checking whether admin user already exists: $ADMIN_EMAIL"
if docker exec -i "$DCONT" bash -lc "/dspace/bin/dspace user -L" 2>/dev/null | grep -qiF "$ADMIN_EMAIL"; then
  echo "✅ Admin already exists: $ADMIN_EMAIL"
  exit 0
fi

echo "==> Admin not found. Creating administrator: $ADMIN_EMAIL"
docker exec -it "$DCONT" bash -lc "/dspace/bin/dspace create-administrator" <<EOF
$ADMIN_EMAIL
$ADMIN_FIRST
$ADMIN_LAST
y
$ADMIN_PASS
$ADMIN_PASS
EOF


echo "==> Verifying admin was created..."
if docker exec -i "$DCONT" bash -lc "/dspace/bin/dspace user -L" 2>/dev/null | grep -qiF "$ADMIN_EMAIL"; then
  echo "✅ Admin created successfully: $ADMIN_EMAIL"
else
  echo "❌ Admin creation did not verify. Check backend logs: docker logs --tail 200 $DCONT"
  exit 1
fi
