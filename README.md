# IOBuildersBank - RESTful Banking Application

A RESTful banking application that supports user management, wallet operations, and secure authentication using JWT. This project demonstrates how to manage users, wallets, and transactions while integrating with blockchain for additional wallet functionality.

---

## Table of Contents
- [Hex Architecture approach](#hex-architecture)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Endpoints](#endpoints)
  - [Authentication](#authentication)
  - [User Management](#user-management)
  - [Wallet Management](#wallet-management)
- [Example JSON Responses](#example-json-responses)

---
## My Hexagonal Architecture Approach

This project is designed using **Hexagonal Architecture (Ports and Adapters)** and DDD to ensure a clean separation of concerns and maintainability.
### Bounded Contexts and Use Case Grouping

For each **Bounded Context**, such as `User` or `Wallet`:
- Business rules and use cases are encapsulated in a **single service interface**, implemented in the **Application Layer**.
- While individual use cases could be split into separate classes, grouping them into a cohesive service interface helps reduce the number of classes, making the codebase cleaner and easier to navigate

  
### Core Design Principles

1. **Encapsulation of Input and Output Logic**:
   - **Input**: Defined through interfaces in the domain layer to handle the use cases of the application. These interfaces specify how the application core interacts with external systems.
   - **Output**: Interfaces are defined for operations like database persistence and retrieval. These interfaces abstract the interaction with external systems like databases or third-party APIs.

2. **Infrastructure Implementations**:
   - The input and output interfaces are implemented in the **Infrastructure Layer**, keeping the core domain independent of specific technologies.
   - If there is a need to replace a database adapter or the input mechanism (e.g., switch from an HTTP API to a message queue), only the infrastructure implementations need to change. The application core remains consistent.

3. **Entity-DTO Mapping**:
   - The infrastructure layer contains **mappers** to convert entities between:
     - **Business Logic Entities**: Used within the core domain.
     - **DTOs (Data Transfer Objects)**: Used for input and output through external ports.
   - This separation ensures a clean boundary between layers, preserving the independence of the core domain.

### Testing Strategy

1. **Unit Tests**:
   - Extensive unit tests have been written for:
     - Service implementations in the **Application Layer**.
     - Input and output ports to ensure that the core domain logic adheres to the defined interfaces.

2. **Integration Tests**:
   - Integration tests are implemented in the **Bootloader Module**.
   - These tests verify the end-to-end behavior of the application, ensuring that all components work seamlessly together.

---

## Technologies Used
- **Spring Boot** for REST API development  
- **H2 Database** for persistent storage  
- **Spring Security** for authentication and authorization  
- **JWT** for stateless authentication
- **OpenApi 3.0** for API First design and implementation of the APIs   
- **Web3j** for blockchain integration with Ganache  
- **Maven** for project build and dependency management  

---

## Setup Instructions

1. Clone repository:
  ```bash
  git clone https://github.com/your-repo-name.git
  cd IOBuildersBank
  ```
   
2. Install dependencies:
  ```bash
  mvn clean install
  ```
3. Run the application:

```bash
cd bootloader
mvn spring-boot:run
```
4. Access the API at http://localhost:8080

## Getting Started

Before running the application, here are a few key considerations to ensure a smooth setup:

### Database Configuration

- **Database URL**: The application uses an embedded H2 database.
- **Access Credentials**:
  - **Username**: `santiago`
  - **Password**: `iobuilders`
- **H2 Console**: The database can be accessed via the H2 console at:
  - [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - Ensure the H2 console is enabled by checking the `application.properties` file:
    ```properties
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console
    ```

### Initialization Script

An initialization script runs automatically when the application starts. It seeds the database with the following data:

- **User**:
  - **Username**: `santisr117`
  - **Password**: `123123` (hashed and stored securely in the database)
  - **Email**: `santiagosr117@gmail.com`
- **Wallets**:
  - `wallet1`: Balance of 100.00
  - `wallet2`: Balance of 50.00

These wallets are linked to the above user.

### Password Hashing

For enhanced security, passwords are stored in the database as hashed values using **BCrypt**. This ensures that sensitive user data remains protected.

## Authentication

The application uses **JWT-based authentication**. 

To log in, create a new user first, or use the `/login` endpoint with the following credentials:

- **Username**: `santisr117`
- **Password**: `123123`

Upon successful login, a JWT token will be returned. This token must be included in the `Authorization` header as a Bearer token for all subsequent requests requiring authentication.

## Running the Application

To run the application:
1. Start the Spring Boot application.
2. Access the H2 console (if needed) at [http://localhost:8080/h2-console](http://localhost:8080/h2-console) with the credentials specified above.
3. Use the `/login` endpoint to authenticate and obtain a JWT token.
4. Test other endpoints using the JWT token for secure access.

---




