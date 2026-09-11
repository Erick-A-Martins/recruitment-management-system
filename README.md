# Recruitment Management System

Hello! 👋
This is a full-stack recruitment management application built as a learning project during Rocketseat's Java course track. It allows companies to manage job openings and candidates to create accounts, browse jobs, and apply for positions.

## Project Structure

The frontend and backend live in the same repository, with clear responsibilities:

- **Frontend:** server-rendered pages built with Thymeleaf, located in `src/main/resources/templates`, with static assets in `src/main/resources/static`.
- **Backend:** Spring Boot application code in `src/main/java`, organized by domain modules (`candidate`, `company`, and `job`) and split into controllers, services, repositories, entities, DTOs, and mappers.
- **API:** REST endpoints are exposed alongside the web interface; interactive documentation is available through Swagger/OpenAPI when the application is running.

## Technologies

- Java 21 and Spring Boot
- Spring MVC, Spring Data JPA, and Spring Security
- Thymeleaf and Thymeleaf Spring Security Extras
- PostgreSQL
- JWT authentication
- Maven, Docker
- Swagger/OpenAPI, Actuator, Prometheus, and Grafana
- JUnit, H2, and JaCoCo for testing and coverage

## Running Locally

Start PostgreSQL and the monitoring services:

```bash
docker compose up -d
```

Then run the application:

```bash
./mvnw spring-boot:run
```

The application runs at `http://localhost:8080`.

## Learning Purpose

This repository was created for educational purposes as part of Rocketseat's Java course track.
