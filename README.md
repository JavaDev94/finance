# 📌 Finance Management API

This is a REST API for managing users and financial transactions, built with **Spring Boot (Java 21)** and **PostgreSQL**.

---

## 🚀 Features
✔️ **User Management**: Create, update, and retrieve users.  
✔️ **Transaction Handling**: Record transactions and update balances.  
✔️ **Filtering**: Get transactions by user and date range.  
✔️ **Database**: Uses PostgreSQL with Liquibase migrations.  
✔️ **Containerized**: Run with Docker and Docker Compose.

---

## 📦 Requirements
Before running the project, ensure you have installed:
- [JDK 21](https://adoptium.net/)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Docker & Docker Compose](https://www.docker.com/products/docker-desktop)
- [PostgreSQL](https://www.postgresql.org/) (if not using Docker)

---

## 🔧 Project Setup


## Build the Project
Make sure you have Maven installed, then run:
``` sh
mvn clean package -DskipTests
```
This will generate a JAR file in the target/ directory.

🐳 Running with Docker
## Option 1: Run with Docker Compose
The easiest way to start the app is using Docker Compose:

``` sh 
    docker-compose up -d
 ```   
    

This will:

Start the PostgreSQL database.
Start the Spring Boot application.
To stop the services, run:
```sh
   docker-compose down
```
 ## Option 2: Run with Manual Docker Commands
1. Build the Docker Image
```sh
  docker build -t finance-app .
```
2. Run the Database Container
```sh
 docker run -d --name finance-db -e POSTGRES_DB=finance -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=secret -p 5432:5432 postgres:15
```
3. Run the Application Container
``` sh
  docker run -d --name finance-app --link finance-db -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://finance-db:5432/finance -e SPRING_DATASOURCE_USERNAME=admin -e SPRING_DATASOURCE_PASSWORD=secret finance-app
```

## 🛠 Running Without Docker
If you want to run the project without Docker:

1️⃣ Start PostgreSQL
Ensure a PostgreSQL instance is running and update your application.properties
``` sh
    spring.datasource.url=jdbc:postgresql://localhost:5432/finance
    spring.datasource.username=postgres
    spring.datasource.password=password
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=none
    spring.liquibase.change-log=classpath:/db.changelog/db.changelog-master.yml
    spring.liquibase.enabled=true
```
2️⃣ Run the Spring Boot Application
```sh
    mvn spring-boot:run
```
## 🔥 API Endpoints
Method  Endpoint  Description

 POST  | /api/v1/users  | Create a new user 

 GET  | /api/v1/users/{id} | Get user details 

 PUT  | /api/v1/users/{id} | Update user information 

 POST   | /api/v1/transactions | Create a new transaction 

 GET  | /api/v1/transactions?from=2024-01-01&to=2024-02-01 | Get transactions by date

📌 Base URL: http://localhost:8080