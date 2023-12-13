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

Execute clean install.

Environment Variables:
- `BBDD_USER`
- `BBDD_PWD`

To see all the coverage of the test, you must see the result of jacoco.

## Usage

- H2 Console (admin/admin): **http://localhost:8080/management/h2-console/**
- Swagger: **http://localhost:8080/management/swagger-ui/index.html**
- Jacoco Coverage result: **http://localhost:63342/superheroes/boot/target/site/jacoco-aggregate/index.html**
