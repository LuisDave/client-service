# Client Service

Microservicio Spring Boot para registrar y consultar clientes pagadores.

## API

- `POST /api/v1/clients`
- `GET /api/v1/clients/{id}`
- `GET /api/v1/clients`

Swagger: `http://localhost:8082/swagger-ui.html`.

## Ejecutar

```powershell
docker compose up -d
.\mvnw.cmd spring-boot:run
```

La base `clients_db` se crea mediante Docker Compose.

## Configuración

- `DB_URL` (por defecto: MySQL local en puerto `3307`)
- `DB_USER` (por defecto: `payment_user`)
- `DB_PASSWORD` (por defecto: `payment_pass`)

## Pruebas y monitoreo

```powershell
.\mvnw.cmd test
```

- Health: `http://localhost:8082/actuator/health`
- OpenAPI JSON: `http://localhost:8082/v3/api-docs`
