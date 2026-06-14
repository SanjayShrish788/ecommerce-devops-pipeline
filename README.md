# E-Commerce Management System with DevOps Pipeline

A full-stack E-Commerce Management System built with Spring Boot, MySQL, and Thymeleaf, featuring a fully containerized architecture (Docker) and an automated CI/CD pipeline (Jenkins).

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue)

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

## Default Credentials
The database is pre-seeded with an admin user.
- **Username:** `admin`
- **Password:** `admin123`

---

## 🚀 How to Run (Using Docker)

The easiest way to run the application is using Docker Compose. This will automatically set up the MySQL database, seed the initial data, build the application, and start it up.

### Prerequisites
- Docker and Docker Desktop installed and running.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/SanjayShrish788/ecommerce-devops-pipeline.git
   cd ecommerce-devops-pipeline
   ```
2. Build and start the containers:
   ```bash
   docker-compose up --build -d
   ```
3. Open your browser and navigate to:
   - **Shop/Customer view:** `http://localhost:8080`
   - **Admin Login:** `http://localhost:8080/login`
   
4. To stop the application:
   ```bash
   docker-compose down
   ```

---

## 💻 How to Run (Local Development)

If you wish to run the app directly via Maven without Docker for the application server (you still need MySQL):

### Prerequisites
- Java 17
- Maven 3.x
- MySQL running locally on port 3306

### Steps
1. Create a database named `ecommjava` in your local MySQL instance.
2. Verify the username and password in `src/main/resources/application.properties` (defaults are root/root).
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Git Branching Strategy
- `main` — stable production-ready code
- `develop` — feature integration branch
- `feature/*` — individual feature branches
