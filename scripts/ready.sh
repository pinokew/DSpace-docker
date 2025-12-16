#!/usr/bin/env bash
set -euo pipefail

BASE_UI="http://localhost:${DSPACE_UI_PORT:-4000}"
BASE_REST="http://localhost:${DSPACE_REST_PORT:-8080}/server"

echo "==> Containers:"
docker compose ps

echo
echo "==> Health:"
docker inspect --format='{{.Name}} {{if .State.Health}}{{.State.Health.Status}}{{else}}no-healthcheck{{end}}' \
  dspace dspacedb dspacesolr dspace-angular 2>/dev/null || true

echo
echo "==> REST check: ${BASE_REST}/api/core/sites"
curl -fsS --max-time 8 "${BASE_REST}/api/core/sites" >/dev/null && echo "REST_OK" || { echo "REST_FAIL"; exit 1; }

echo
echo "==> UI check: ${BASE_UI}/"
curl -fsS --max-time 8 "${BASE_UI}/" >/dev/null && echo "UI_OK" || { echo "UI_FAIL"; exit 1; }

echo
echo "==> SSR check from UI container -> backend"
docker exec -t dspace-angular sh -lc "timeout 8 wget -qO- http://dspace:8080/server/api/core/sites >/dev/null && echo SSR_OK || echo SSR_FAIL"
