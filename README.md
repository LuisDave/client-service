# Client Service

Microservicio responsable de registrar y consultar los clientes que actúan como pagadores en el flujo de pagos.

## Responsabilidades

- Registrar clientes.
- Consultar un cliente por su identificador.
- Listar los clientes registrados.
- Exponer un contrato HTTP documentado con OpenAPI.

El servicio es dueño exclusivo de la base de datos `clients_db`. Otros servicios deben validar clientes a través de su API, nunca leyendo esta base directamente.

## Requisitos

- Java 21.
- Docker Desktop, para iniciar MySQL.

## Ejecutar localmente

Inicia la base de datos:

```powershell
docker compose up -d
```

Inicia el servicio:

```powershell
.\mvnw.cmd spring-boot:run
```

El servicio queda disponible en `http://localhost:8082`.

## API

| Método | Ruta | Descripción |
| --- | --- | --- |
| `POST` | `/api/v1/clients` | Registra un cliente pagador. |
| `GET` | `/api/v1/clients/{clientId}` | Consulta un cliente por ID. |
| `GET` | `/api/v1/clients` | Lista los clientes registrados. |

Ejemplo de registro:

```json
{
  "fullName": "Ana López",
  "email": "ana@example.com"
}
```

La especificación OpenAPI está disponible en `http://localhost:8082/v3/api-docs` y Swagger UI en `http://localhost:8082/swagger-ui.html`.

## Configuración

| Variable | Valor local predeterminado |
| --- | --- |
| `DB_URL` | `jdbc:mysql://localhost:3307/clients_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC` |
| `DB_USER` | `payment_user` |
| `DB_PASSWORD` | `payment_pass` |

El esquema se controla mediante `src/main/resources/db/migration/V1__create_clients.sql`; Hibernate únicamente lo valida.

## Pruebas y monitoreo

```powershell
.\mvnw.cmd test
```

- Salud: `http://localhost:8082/actuator/health`
- Información: `http://localhost:8082/actuator/info`

## Decisiones de diseño

La capa de aplicación depende del puerto `IClientRepository`, mientras que `ClientPersistenceAdapter` contiene los detalles de JPA. El reloj se inyecta como dependencia y usa UTC, lo que evita que la lógica de registro dependa de la zona horaria del servidor y permite pruebas deterministas.
