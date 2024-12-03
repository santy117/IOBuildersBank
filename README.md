# IOBuildersBank - RESTful Banking Application


## Table of Contents
- [Hex Architecture approach](#hex-architecture)
- [Technologies Used](#technologies-used)
- [Getting started](#setup-instructions)
- [Blockchain integration](#blockchain-integration)
- [Endpoints](#endpoints)

---
## My Hexagonal Architecture Approach

This project is designed using Hexagonal Architecture and DDD to ensure a clean separation of concerns and maintainability.
### Bounded Contexts and Use Case Grouping

For each **Bounded Context**, such as `User` or `Wallet`:
- Business rules and use cases are encapsulated in a single service interface, implemented in the Application Layer. While individual use cases could be split into separate classes, I find it cleaner by
  grouping them into a service interface for each context, specially if the application scales too much.

  
### Core Design Approach

1. **Input/Output Encapsulation**:  
   - **Input**: Interfaces in the domain layer define use case interactions.  
   - **Output**: Interfaces abstract external systems, in this case H2 database.

2. **Infrastructure Implementations**:  
   - Interfaces are implemented in the infrastructure layer, allowing for easy replacement of external adapters without affecting the core logic.

3. **Entity-DTO Mapping**:  
   - Mappers convert business logic entities and DTOs for input and output, maintaining separation between layers and preserving core domain independence.

### Testing
- **Unit Tests**: Cover application services and input/output ports.
- **Integration Tests**: Verify end-to-end functionality in the Bootloader module with preinjected data.

---

## Technologies Used
- **Java 17**
- **Spring Boot**
- **H2 Database**  
- **Spring Security**
- **JWT Authentication**
- **OpenApi 3.0**
- **Web3j**
- **Truffle and Ganache**
- **Maven**

---

## Getting Started

### Setup Instructions

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

### Access the API and H2 Database

- **Base URL**: `http://localhost:8080`
- **H2 Console**: `http://localhost:8080/h2-console`
  - **Username**: `santiago`
  - **Password**: `iobuilders`

### Initialization Script

The application seeds the database with the following data:

- **User**:
  - Username: `santisr117`
  - Password: `123123` (password is securely hashed)
  
- **Wallets**:
  - `wallet1`: Balance 100.00
  - `wallet2`: Balance 50.00

These wallets are linked to the above user, and this data is used to test the integration tests and to allow an initial configuration of the bank application.

### Password Hashing

For enhanced security, passwords are stored in the database as hashed values using **BCrypt**.

## Authentication

The API uses **JWT-based authentication**.

### Login

- **Endpoint**: `POST /login`

**Input** (JSON):
```json
{
  "username": "santisr117",
  "password": "123123"
}


---




