# AUDIT_REPORT

## Звіт з аудиту безпеки (DSpace Stack)

Дата: 2026-02-23  
Середовище: продакшн-подібний деплой (`repo.fby.com.ua`)  
Обсяг: рівень застосунку, reverse proxy, host OS

## 1. Мета

Перевірити стан безпеки на трьох рівнях:
- рівень застосунку DSpace/Docker
- рівень reverse proxy (Traefik)
- рівень хоста (Ubuntu 24.04)

## 2. Підсумок (Executive Summary)

Статус: **часткова відповідність** цільовому профілю безпеки.  
Ключові hardening-зміни впроваджені та перевірені (заголовки, обробка проксі-заголовків, CI vulnerability gates).  
На рівні хоста лишаються відкриті пункти (явна SSH-політика, `fail2ban`, UFW, сторонні відкриті порти поза DSpace).

## 3. Перевірені контролі та докази

### 3.1 Application Level (DSpace/Docker)

- Модель мережевої експозиції:
`dspace-traefik` прив’язаний до `127.0.0.1:8080->80/tcp` (не `0.0.0.0`).
- Обробка секретів:
`.env` присутній з правами `-rw-------` (600).
- Сканування вразливостей у CI:
увімкнено `Trivy config` + `Trivy image` gate на `CRITICAL`.
- Керування винятками:
`.trivyignore.yaml` з дедлайнами та обґрунтуванням, контроль прострочення в CI.

Фактичні докази:
- `docker compose ps` показав localhost-прив’язку Traefik.
- `ls -l .env` підтвердив права `600`.
- CI workflow містить критичні Trivy-gates і перевірку `expired_at`.

### 3.2 Reverse Proxy Level (Traefik)

- Forwarded headers:
middleware Traefik встановлює `X-Forwarded-Proto=https`, `X-Forwarded-Port=443`.
- Security headers:
у відповідях UI та API присутні:
`X-Content-Type-Options: nosniff`,
`X-Frame-Options: SAMEORIGIN`,
`Referrer-Policy: strict-origin-when-cross-origin`,
`Content-Security-Policy-Report-Only: ...`.
- Cookie-поведінка:
API повертає XSRF-cookie з прапорами `Secure; SameSite=None`.
- CORS-безпека:
preflight від `https://evil.example` повертає `403`.

Фактичні докази (live curl):
- `https://repo.fby.com.ua/` повертає очікувані security headers.
- `https://repo.fby.com.ua/server/api/core/sites` повертає очікувані headers + secure cookie.
- OPTIONS preflight із чужим origin повертає `HTTP/2 403`.

### 3.3 Host OS Level (Ubuntu 24.04)

- `unattended-upgrades`:
`enabled` + `active`.
- `fail2ban`:
не встановлено (`not-found`), `inactive`.
- UFW:
у `/etc/ufw/ufw.conf` встановлено `ENABLED=no`.
- SSH hardening:
виявлено `KbdInteractiveAuthentication no`, `UsePAM yes`;
явних `PasswordAuthentication no` / `PubkeyAuthentication yes` у файлах конфігурації не знайдено.
- Docker privilege model:
активний користувач входить до групи `docker` (root-equivalent рівень доступу).
- Прослуховувані порти:
для DSpace Traefik локальний, але на хості лишаються сторонні TCP-порти `21115-21119`.

## 4. Зміни, впроваджені під час аудиту

- `docker-compose.yml`:
localhost-bind Traefik за замовчуванням, hardening forwarded headers, security middleware, підключення middleware до UI/API роутерів.
- `scripts/patch-local.cfg.sh`:
встановлюється `server.forward-headers-strategy=framework`.
- `scripts/verify-env.sh`:
додано обов’язкову перевірку прав `.env` (`600`).
- `scripts/smoke-test.sh`:
додано перевірки security headers + CORS, покращено парсинг заголовків.
- `.github/workflows/ci-cd.yml`:
додано жорсткі Trivy critical-gates, контроль прострочення ignore-записів, діагностику CVE по образах.
- `.trivyignore.yaml`:
впроваджено керований реєстр прийнятих ризиків (з дедлайном і поясненням).

## 5. Залишкові ризики / винятки

- Базова політика host firewall не активована (`UFW ENABLED=no`).
- Відсутній `fail2ban`.
- Політика SSH “лише ключі” не зафіксована явно у конфігах.
- Зовнішні порти `21115-21119` (не DSpace) залишаються відкритими.
- У `.trivyignore.yaml` є прийняті upstream CVE-винятки (обмежені в часі, обов’язковий перегляд до дедлайну).

## 6. Перевірка Definition of Done

- Короткий звіт (що перевірено/виправлено/ризики): **виконано**.
- Security headers видно на UI та `/server`: **виконано**.
- Порти відповідають обраній моделі доступу (Tunnel vs direct): **частково виконано**.
DSpace-потік працює в tunnel-safe моделі, але на хості є сторонні відкриті порти.
- SSH лише за ключем: **не повністю виконано** (явна конфігурація не підтверджена).

## 7. Рекомендовані наступні кроки

1. Явно зафіксувати SSH key-only у `sshd_config`:
`PasswordAuthentication no`, `PubkeyAuthentication yes`, `PermitRootLogin no` (або `prohibit-password`), далі безпечний reload SSH.
2. Встановити й увімкнути `fail2ban` з SSH jail.
3. Увімкнути та застосувати UFW-політику відповідно до обраної моделі:
tunnel-only (закрити публічні 80/443) або direct-access (відкрити лише 80/443 + обмежений SSH).
4. Переглянути сторонні сервіси з портами `21115-21119`; ізолювати або обмежити firewall-правилами.
5. Щомісяця переглядати `.trivyignore.yaml` і видаляти винятки після появи виправлених upstream-образів.
