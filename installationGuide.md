# BOOKLY
## Installation Guide

### 1. Prerequisites

- Maven
- npm
- Docker
- Keycloak on Port 8180 (accessible) or use our running instance at keycloak.bookly.online

### 2.Installation

### 2.1 Clone code from directory via URL or HTTPS:

URL:    git@gitlab.com:project_bookly/bookly.git 

HTTPS:  https://gitlab.com/project_bookly/bookly.git

### Change the application properties of the backend
-> backend/src/main/resources/application.properties
```
server.port=8080

keycloak.auth-server-url=https://keycloak.bookly.online/auth
spring.datasource.hikari.auto-commit=true
#database
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/bookly-dev
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=booklyUser
spring.datasource.password=booklyPW
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```
### 2.2 Build the project in the root folder of the project.
```
mvn install -DskipTests
```

### 2.3 Go to the backend folder 

	Execute: cd backend
	
### 2.4 Launch bookly with.

	docker-compose up

### 2.5 Go to localhost:8080 to use bookly.


