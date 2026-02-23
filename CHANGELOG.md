# CHANGELOG

## [2026-02-23] Посилення безпеки + security gates у CI/CD

### Додано
- Додано middleware безпеки Traefik для UI та API маршрутів:
`X-Content-Type-Options`, `X-Frame-Options`, `Referrer-Policy`, `Content-Security-Policy-Report-Only`.
- Додано примусове встановлення forwarded-заголовків на рівні проксі:
`X-Forwarded-Proto=https`, `X-Forwarded-Port=443`.
- Додано `Trivy image` gate у CI для `CRITICAL` знахідок у всіх образах compose.
- Додано керований реєстр винятків Trivy у `.trivyignore.yaml` з полями:
`id`, `expired_at`, `statement`.
- Додано перевірку політики в CI для прострочених винятків Trivy (`expired_at`).
- Додано явну діагностику Trivy по кожному образу в CI:
список проблемних образів + витяг неігнорованих `CVE-*`.
- Додано security-перевірки у `scripts/smoke-test.sh`:
заголовки UI/API, захист від CORS-антипатерну (`ACAO=*` + credentials), стабільний регістронезалежний парсинг заголовків.

### Змінено
- Змінено host-binding Traefik на безпечний локальний дефолт:
`${TRAEFIK_BIND_IP:-127.0.0.1}:${TRAEFIK_ENTRYPOINT_PORT:-8080}:80`.
- Змінено патчинг backend у `scripts/patch-local.cfg.sh`:
встановлюється `server.forward-headers-strategy=framework` для коректної обробки проксі-заголовків.
- Змінено валідацію env у `scripts/verify-env.sh`:
додано вимогу до прав `.env` = `600` (поза CI mock mode).
- Змінено режим Trivy image scan на перевірку лише вразливостей (`--scanners vuln`) для чіткішого gate та швидшого виконання.

### Видалено
- Видалено legacy-файл `.trivyignore` (plain text).
- Мігровано на керований формат `.trivyignore.yaml`.

### Нотатки з безпеки
- CI/CD тепер падає на неігнорованих `CRITICAL` знахідках у конфігах та образах.
- Прийняття ризику стало явним, обмеженим у часі та контрольованим через `.trivyignore.yaml`.
- Security headers тепер автоматично перевіряються під час deploy smoke-тестів.

### Операційний вплив
- Публічна пряма експозиція Traefik більше не є дефолтом.
- Реліз/деплой може блокуватися через:
нові `CRITICAL` CVE, прострочені security-винятки, відсутні обов’язкові заголовки, небезпечну CORS-політику.
