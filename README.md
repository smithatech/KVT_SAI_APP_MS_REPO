# Exhibition Management System API (Spring Boot)

Implements the OpenAPI spec for managing exhibitions, modules, stalls, content, questionnaires, and user preferences.

## Quick start

Requirements: Java 17+, Maven 3.9+

```bash
mvn spring-boot:run
```

- Base URL (as per spec): `http://localhost:8080/kvcapp/v1/event`
- Swagger UI: `http://localhost:8080/kvcapp/v1/event/swagger-ui.html`
- H2 Console: `http://localhost:8080/kvcapp/v1/event/h2-console` (JDBC URL: `jdbc:h2:mem:ems`)

### Auth
No authentication is currently enabled. Endpoints are publicly accessible.
Use header `X-User-Id: user-1` for `/me/*` endpoints to scope user-specific data.

### Sample curl

```bash
# list exhibitions
curl -s 'http://localhost:8080/kvcapp/v1/event/exhibitions'
```

### Switch database
All DB config is in `src/main/resources/application.properties`. To point to Postgres or MySQL, comment H2 and set the JDBC URL, driver, username, and password, then add the driver to `pom.xml`.

### Notes
- No authentication layer is present; add your preferred security later.
- Entities map closely to the OpenAPI schemas. For brevity, DTOs and entities are lightly separated.
- data.sql seeds sample data you can immediately query.
