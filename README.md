# 🏠 Rental Property Management API

This is the **Spring Boot backend** for the [Rental Property Management](https://github.com/mayanklad/rental-manager) project. It provides RESTful APIs to manage properties, tenants, lease, rent, and maintenance operations.

## 🚀 Features

- CRUD APIs for rental property data  
- PostgreSQL integration  
- JPA/Hibernate for ORM  
- Swagger UI for auto-generated API docs  
- Docker support for backend container  
- Environment-based configuration (via Docker Compose)  
- CORS support (coming soon)

## 📚 Tech Stack

- **Java 21**  
- **Spring Boot 3.5.0**  
- **Spring Data JPA**  
- **PostgreSQL**  
- **Springdoc OpenAPI** (Swagger)  
- **Docker**  
- **Maven**  

## ⚙️ Configuration

> All configuration (DB credentials, app name, etc.) is managed via **environment variables** defined in the `docker-compose.yml` file located in the **main parent repository** 🔗 [Rental Property Management](https://github.com/mayanklad/rental-manager).

If you’re running locally **outside Docker Compose**, create an `application.properties` in `src/main/resources/`:

```properties
# Application name
spring.application.name=rental-manager-api

# PostgreSQL DB config
spring.datasource.url=jdbc:postgresql://localhost:5432/rental_db
spring.datasource.username=rental_user
spring.datasource.password=rental_pass

# JPA config
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Backend server port
server.port=8080

```

## 📦 Running the App

### 🔧 Local (via Maven)

```bash
./mvnw spring-boot:run
```

App will run at:  
➡️ `http://localhost:8080`

## 🐳 Docker Support

This repo includes a `Dockerfile` to containerize the Spring Boot API.

### 1. Build Docker Image

```bash
docker build -t rental-manager-api .
```

### 2. Run Container

```bash
docker run -p 8080:8080 rental-manager-api
```

> ⚠️ This runs the backend only. A running PostgreSQL instance is required.

## 🧩 Docker Compose Setup

The `docker-compose.yml` (with Spring Boot + PostgreSQL) is located in the **main parent project repo**:  
🔗 [Main Project Repository](https://github.com/mayanklad/rental-manager)

To spin up the complete app:

```bash
cd /navigate-to-the-main-repo
docker-compose up --build
```

## 📖 API Documentation (Swagger UI)

Swagger is auto-generated and available at:

➡️ [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/rentalmanager/api/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── repository/
│   │   ├── service/
│   │   └── RentalManagerApiApplication.java
│   └── resources/
│       └── (application.properties - optional for local only)
├── test/
├── Dockerfile
├── LICENSE
├── pom.xml
└── README.md
```

## 🧾 Build Tool & Dependencies

Managed with **Maven**, using `spring-boot-starter-*` dependencies including:

- `spring-boot-starter-data-jpa`
- `spring-boot-starter-web`
- `spring-boot-starter-validation`
- `springdoc-openapi-starter-webmvc-ui`
- `lombok`
- `postgresql`
- `spring-boot-devtools` (optional)

Full details in [`pom.xml`](./pom.xml)

## 📝 License

This project is licensed under the **GNU GPL-3.0 License**.

## 🔗 Related Repositories

- **Frontend (React + Tailwind CSS)**:  
  [https://github.com/mayanklad/rental-manager-ui](https://github.com/mayanklad/rental-manager-ui)

- **Main Project (with Docker Compose)**:  
  [https://github.com/mayanklad/rental-manager](https://github.com/mayanklad/rental-manager)
