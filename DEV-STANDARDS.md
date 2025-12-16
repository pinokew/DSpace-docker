UI режим: Angular dev-server (npm run serve / ng serve) — не prod SSR.

REST: dspace (tomcat/boot) на :8080, API namespace /server.

Docker files (канонічні):

docker-compose.yml — backend + db + solr

docker-compose.dev.yml — тільки dev UI

ui-config/config.yml — UI config (маунт read-only)

example.env — шаблон; .env локально/на сервері

scripts/bootstrap-admin.sh — створення адміна

scripts/ready-check.sh — перевірка готовності

Volumes (must persist): pgdata, assetstore, solr_data, + конфіги ./dspace/config:/dspace/config, ./ui-config/config.yml:/app/config/config.yml:ro

Логи: json-file з ротацією.