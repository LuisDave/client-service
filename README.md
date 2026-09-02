# Client Service

Servicio propietario de los clientes que actúan como pagadores. `payment-service` consulta esta API para comprobar que un `payerId` existe antes de registrar un pago.

## Especificación técnica

| Aspecto | Valor |
| --- | --- |
| Runtime | Java 21 / Spring Boot 4.1.1 |
| Puerto HTTP | `8082` |
| Persistencia | MySQL, base `clients_db` |
| Esquema | `src/main/resources/db/migration/V1__create_clients.sql` |
| Documentación | Swagger `http://localhost:8082/swagger-ui.html` |
| Observabilidad | `GET /actuator/health` |

La tabla `clients` mantiene `id`, `full_name`, `email` único y `created_at`. Hibernate sólo valida el esquema; no lo modifica.

## API

| Método | Ruta | Resultado |
| --- | --- | --- |
| `POST` | `/api/v1/clients` | Registra un cliente y responde `201`. |
| `GET` | `/api/v1/clients/{clientId}` | Devuelve un cliente o `404`. |
| `GET` | `/api/v1/clients` | Devuelve todos los clientes. |

Solicitud de alta:

```json
{
  "fullName": "Ana López",
  "email": "ana@example.com"
}
```

`fullName` es obligatorio y admite hasta 150 caracteres. `email` es obligatorio, debe ser válido y único.

## Configuración

| Variable | Valor local predeterminado |
| --- | --- |
| `DB_URL` | `jdbc:mysql://localhost:3307/clients_db?...` |
| `DB_USER` | `payment_user` |
| `DB_PASSWORD` | `payment_pass` |

## Ejecutar

Para desarrollo aislado, desde este directorio:

```powershell
docker compose up -d
.\mvnw.cmd spring-boot:run
```

Para levantar todos los servicios y sus dependencias, sigue el manual de [orchestrator](https://github.com/LuisDave/orchestrator). Desde esa carpeta, ejecuta:

```powershell
docker compose up --build -d
```

El `Dockerfile` genera el JAR con Maven y lo ejecuta con JRE 21. El `docker-compose.yml` local sólo inicia MySQL; la orquestación completa vive en el repositorio [orchestrator](https://github.com/LuisDave/orchestrator).

### Pasos locales

1. Inicia Docker Desktop y verifica que los puertos `3307` y `8082` estén libres.
2. Desde `client-service`, ejecuta `docker compose up -d` para iniciar MySQL.
3. Confirma que MySQL esté sano con `docker compose ps`.
4. Inicia la API con `.\mvnw.cmd spring-boot:run`.
5. Comprueba `http://localhost:8082/actuator/health` y abre Swagger.

Para detener únicamente la infraestructura local, usa `docker compose down`.

### Ejecutar el Dockerfile

Con MySQL local iniciado, construye y ejecuta la imagen:

```powershell
docker build -t client-service:local .
docker run --rm --name client-service -p 8082:8082 `
  -e DB_URL="jdbc:mysql://host.docker.internal:3307/clients_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" `
  -e DB_USER=payment_user -e DB_PASSWORD=payment_pass client-service:local
```

En Windows con Docker Desktop, `host.docker.internal` permite que el contenedor acceda al MySQL publicado en el host.

## Pruebas y Postman

```powershell
.\mvnw.cmd test
```

Importa `postman/client-service.postman_collection.json` en Postman y ejecuta **Create client**. La colección guarda automáticamente el ID recibido en la variable `clientId`; después ejecuta **Get client by ID** o **List clients** para comprobar el registro.
