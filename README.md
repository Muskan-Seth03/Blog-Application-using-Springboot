# Blog App APIs

A Spring Boot backend providing REST APIs for a blog application. This project implements user authentication (JWT), role-based access control, and CRUD operations for posts, comments, and user profiles. It is built with Java, Spring Boot, and Maven and is intended to be used as the API server for a blog front-end.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Common Endpoints (examples)]
- [Testing](#testing)
- [Development notes](#development-notes)
- [Contributing](#contributing)

## Features
- RESTful APIs for posts, comments, and users
- JWT-based authentication and authorization
- Role seeding on startup (e.g. ROLE_ADMIN, ROLE_USER)
- Input validation using Spring Validation
- OpenAPI/Swagger documentation via springdoc
- MySQL datasource support using Spring Data JPA

## Tech Stack
- Java 17
- Spring Boot (parent 4.x)
- Spring Web (MVC)
- Spring Data JPA
- Spring Security
- JSON Web Tokens (jjwt)
- ModelMapper for DTO mapping
- Maven for build
- MySQL (runtime)
- springdoc OpenAPI (Swagger UI)

## Prerequisites
- Java 17+ installed and JAVA_HOME configured
- Maven 3.6+ installed and in PATH
- MySQL database (or another supported JDBC datasource)

## Quick Start
1. Clone the repository (already done if you have this project locally):

```bash
git clone https://github.com/Muskan-Seth03/Blog-Application-using-Springboot.git
cd Blog-Application-using-Springboot
```

2. Configure your database and JWT secret (see [Configuration](#configuration)).

3. Build the project:

```bash
mvn clean package -DskipTests
```

4. Run the application (jar produced under `target`):

```bash
java -jar target/blog-app-apis-0.0.1-SNAPSHOT.jar
```

Alternatively, run via Maven (dev-friendly):

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

## Configuration
Application configuration is in `src/main/resources/application.properties` and optional profile files like `application-dev.properties`.

Typical properties to set (example):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=bloguser
spring.datasource.password=secret
spring.jpa.hibernate.ddl-auto=update

# JWT
app.jwtSecret=ReplaceThisWithAStrongSecret
app.jwtExpirationMs=86400000
```

You can also set these via environment variables:
- SPRING_DATASOURCE_URL
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD
- APP_JWTSECRET (or follow your app's property names)

If the project seeds roles or data at startup (CommandLineRunner), ensure the database user has permission to create/read/update those tables.

## API Documentation
This project uses `springdoc-openapi` to provide an interactive Swagger UI. Once the application is running, open:

```
http://localhost:8080/swagger-ui.html
# or
http://localhost:8080/swagger-ui/index.html
```

The API spec (OpenAPI JSON) is usually available at:

```
http://localhost:8080/v3/api-docs
```

## Common Endpoints (examples)
The exact endpoints may vary depending on the controller packages in the source. Typical routes you can expect:
- Authentication: `POST /api/auth/login`, `POST /api/auth/register`
- Posts: `GET /api/posts`, `GET /api/posts/{id}`, `POST /api/posts`, `PUT /api/posts/{id}`, `DELETE /api/posts/{id}`
- Comments: `POST /api/posts/{id}/comments`, `GET /api/posts/{id}/comments`
- Users: `GET /api/users`, `GET /api/users/{id}` (admin only)

Refer to the Swagger UI or controller sources for exact route names and required request/response shapes.

## Testing
Run the unit and integration tests with:

```bash
mvn test
```

## Development notes
- DTOs and entity mapping: ModelMapper is used to map between entities and DTOs.
- Security: JWT tokens are used to secure endpoints. Tokens are created/validated in the security package.
- Lombok is used to reduce boilerplate; if your IDE needs configuration, enable annotation processing.

## Contributing
1. Fork the repository
2. Create a feature branch: `git checkout -b feat/my-feature`
3. Make changes and add tests
4. Commit and push to your fork
5. Open a Pull Request describing the change

Please keep commits small and focused and include tests for new behavior.