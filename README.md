# E-Commerce Management System with DevOps

This is a complete full-stack E-Commerce Management System built with Spring Boot, MySQL, Thymeleaf, and Bootstrap 5. It incorporates DevOps practices including Docker containerization and a Jenkins CI/CD pipeline.

## Features
- **Customer:** Register, Login, Browse Products, Add to Cart.
- **Admin:** Login, Dashboard statistics, Manage Products, Manage Categories, View Customers.
- **Security:** Spring Security with BCrypt password hashing and session-based authentication.
- **DevOps:** Docker-compose setup for easy deployment, Jenkinsfile for CI/CD pipeline.

## Architecture
- **Backend:** Java 17, Spring Boot 3.x (Spring MVC, Spring Data JPA, Spring Security)
- **Database:** MySQL 8.0
- **Frontend:** Thymeleaf, Bootstrap 5, HTML5, CSS3
- **Build Tool:** Maven

## Prerequisites
- Java 17
- Maven 3.x
- Docker & Docker Compose
- MySQL (if running locally without Docker)

## Local Setup
1. Clone the repository.
2. (Optional) Run `docker-compose up -d mysql` to start the local MySQL container.
3. Run `mvn spring-boot:run` to start the application.
4. Access the app at `http://localhost:8080`.

## Docker Setup
1. Run `docker-compose up --build -d`
2. The app will be available at `http://localhost:8080`.

## Git Branching Strategy
- `main` — stable production-ready code
- `develop` — feature integration branch
- `feature/*` — individual feature branches
