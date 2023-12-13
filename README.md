## Summary

Superheroes CRUD application to W2M: https://github.com/juaneng95/superheroesW2M

## Technology

- Spring boot framework 3.2.0
- Java 21.0.1
- OpenApi 3.0.1
- Maven 3.8.8
- Jacoco 0.8.11
- Mockito 5.8.0
- Flyway Core 9.22.3
- H2 Database 2.1.220

## Execution

Configure the project structure with:
- `SDK`: 21
- `Language Level`: 21
- 
Execute `clean install` with the maven correctly configured.

Environment Variables (Run):
- `BBDD_USER` = admin
- `BBDD_PWD` = admin

To see all the test coverage you must see the jacoco or run with coverage "Superheroes tests".

## Usage

- H2 Console (admin/admin): **http://localhost:8080/management/h2-console/**
- Swagger: **http://localhost:8080/management/swagger-ui/index.html#/**
- Jacoco Coverage result: **http://localhost:63342/superheroesW2M/target/site/jacoco/index.html**
- Docker: docker build -t superheroes:latest .