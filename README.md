# Spring REST API Boilerplate

This repository provides a multi-module Spring Boot project that can be used as a starting point for RESTful services.

## Modules

- `module-api` – entry point for REST APIs. Includes sample CRUD endpoints, security settings and OpenAPI (Swagger) documentation.
- `module-common` – common utilities such as a global exception handler, response wrapper and request context interceptor.
- `module-infra` – infrastructure libraries used by the API
  - `file` – local file storage helper
  - `email` – email sending via `JavaMailSender`
  - `http-client` – helper classes for `RestClient`, `WebClient` and an HTTP interface example

## Getting Started

### Prerequisites

- Java 17
- Gradle (use the provided wrapper)

### Build & Test

```bash
./gradlew build
```

### Run Locally

A docker compose file is provided to start a MySQL instance for local development:

```bash
cd module-api/compose
docker compose -f test-db-docker-compose.yml up -d
```

Start the API application from the project root:

```bash
./gradlew :module-api:bootRun
```

By default the `local` profile is used (see `application-local.yml`).

OpenAPI documentation is available at `/swagger-ui.html` when the application is running.

## Additional Features

- MapStruct configuration for DTO mapping
- Database auditing via a base entity
- P6Spy logging with custom formatter
- Request context interceptor that assigns a unique request ID

## Directory Structure

```
module-api       // REST API module
module-common    // shared utilities and response classes
module-infra     // infrastructure helpers (file, email, http-client)
```

## License

This project is provided as a boilerplate under the terms of the Apache 2.0 license.
