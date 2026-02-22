#!/usr/bin/env bash
# scripts/smoke-test.sh
# Post-Deploy Smoke tests for DSpace ecosystem (UI/API/OAI/Sitemap)
# - robust .env parsing (no source/eval; supports spaces like: GET, POST, ...)
# - retry with timeouts (services may warm up)
# - API accepts HAL+JSON (application/hal+json)
# - Sitemap check is WARNING-only (nightly generation)
#   and tries both possible URLs (/sitemap_index.xml and /server/sitemaps/sitemap_index.xml)

set -euo pipefail

log()  { echo "[$(date '+%F %T')] $*"; }
fail() { echo "[$(date '+%F %T')] ❌ $*" >&2; exit 1; }

load_env() {
  local env_file="$1"
  [[ -f "$env_file" ]] || fail ".env not found: $env_file"

  while IFS= read -r line || [[ -n "$line" ]]; do
    line="${line%$'\r'}"
    [[ -z "${line//[[:space:]]/}" ]] && continue
    [[ "$line" =~ ^[[:space:]]*# ]] && continue

    # Support both `export KEY=...` and plain `KEY=...` (with optional leading spaces).
    line="$(echo "$line" | sed -E 's/^[[:space:]]*export[[:space:]]+//')"
    [[ "$line" == *"="* ]] || continue

    local key="${line%%=*}"
    local value="${line#*=}"

    key="$(echo "$key" | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')"
    [[ "$key" =~ ^[A-Za-z_][A-Za-z0-9_]*$ ]] || continue

    value="$(echo "$value" | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')"

    if [[ "$value" =~ ^\".*\"$ ]]; then
      value="${value:1:${#value}-2}"
    elif [[ "$value" =~ ^'.*'$ ]]; then
      value="${value:1:${#value}-2}"
    fi

    printf -v "$key" '%s' "$value"
    export "${key?}"
  done < "$env_file"
}

# http_check: returns 0/1 (does NOT exit)
http_check() {
  local name="$1"
  local url="$2"
  local expect_code="${3:-200}"
  local expect_ct_regex="${4:-}"   # e.g. json
  local expect_kw="${5:-}"         # keyword in body
  local attempts="${6:-20}"
  local sleep_s="${7:-5}"

  log "🔎 Checking: $name"
  log "    URL: $url"

  local body_file=""
  body_file="$(mktemp)"
  trap '[[ -n "${body_file:-}" ]] && rm -f "${body_file}"' RETURN

  for ((i=1; i<=attempts; i++)); do
    local out code ct ok=true

    out="$(curl -sS --max-time 12 --connect-timeout 5 \
      -o "$body_file" \
      -w "%{http_code} %{content_type}" \
      "$url" || true)"

    code="${out%% *}"
    ct="${out#* }"

    [[ "$code" == "$expect_code" ]] || ok=false

    if [[ -n "$expect_ct_regex" ]]; then
      echo "$ct" | grep -Eqi "$expect_ct_regex" || ok=false
    fi

    if [[ -n "$expect_kw" ]]; then
      grep -qi "$expect_kw" "$body_file" || ok=false
    fi

    if [[ "$ok" == true ]]; then
      log "✅ OK ($name): HTTP $code, CT='$ct' (attempt $i/$attempts)"
      return 0
    fi

    # Для optional checks типово не треба чекати довго: якщо 404 — швидше за все просто ще не згенеровано.
    # Але ця функція універсальна, тому залишаємо retry, а керуємо ним у warn_check.
    log "… not ready ($name): HTTP $code, CT='$ct' (attempt $i/$attempts)"
    sleep "$sleep_s"
  done

  log "----- Response snippet ($name) -----"
  head -n 30 "$body_file" || true
  log "-----------------------------------"
  return 1
}

require_check() {
  local name="$1"; shift
  if ! http_check "$name" "$@"; then
    fail "Smoke check failed: $name"
  fi
}

warn_check() {
  local name="$1"; shift
  if ! http_check "$name" "$@"; then
    log "⚠️  WARNING: Optional check failed: $name. Not failing deploy."
  fi
  return 0
}

warn_sitemap_check() {
  local name="$1"
  local url1="$2"
  local url2="$3"

  # Пояснення: sitemap генерується вночі, тому:
  # - не чекаємо довго
  # - пробуємо 2 відомі URL (у різних DSpace/Traefik конфігах sitemap може бути в різних місцях)

  log "🔎 Checking: $name"
  log "    URL candidates:"
  log "      1) $url1"
  log "      2) $url2"

  if http_check "$name" "$url1" 200 "" "sitemap" 2 3; then
    return 0
  fi

  if http_check "$name" "$url2" 200 "" "sitemap" 2 3; then
    return 0
  fi

  log "⚠️  WARNING: Sitemap is not available yet (expected after nightly generation). Not failing deploy."
  return 0
}

# ---- Main ----
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd)"
ENV_FILE="$SCRIPT_DIR/../.env"
load_env "$ENV_FILE"

HOST="${DSPACE_HOSTNAME:-repo.fby.com.ua}"

UI_URL="https://${HOST}/"
API_URL="https://${HOST}/server/api/core/sites"
OAI_URL="https://${HOST}/server/oai/request?verb=Identify"

# Two possible sitemap URLs:
SITEMAP_URL1="https://${HOST}/sitemap_index.xml"
SITEMAP_URL2="https://${HOST}/server/sitemaps/sitemap_index.xml"

log "🚦 Smoke tests starting for host: ${HOST}"

require_check "UI Home" "$UI_URL" 200 "" "DSpace" 20 5
require_check "REST API core/sites" "$API_URL" 200 "json" "" 30 3
require_check "OAI Identify" "$OAI_URL" 200 "" "Identify" 20 5

# Sitemap: WARNING-only (nightly generation)
warn_sitemap_check "Sitemap (optional)" "$SITEMAP_URL1" "$SITEMAP_URL2"

log "✅ Required smoke tests passed (Sitemap is optional)."
