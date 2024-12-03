
# IOBuildersBank - RESTful Banking Application


## Table of Contents
- [My Hexagonal Architecture approach](#my-hexagonal-architecture-approach)
- [Technologies Used](#technologies-used)
- [Getting started](#getting-started)
- [Blockchain integration](#blockchain-integration)
- [API](#api)

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
- **BCrypt** 
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
  git clone https://github.com/santy117/IOBuildersBank.git
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
  - Password: `123123` (password is stored in the database as a hash)
  
- **Wallets**:
  - `wallet1`: Balance 100.00
  - `wallet2`: Balance 50.00

These wallets are linked to the above user, and this data is used to test the integration tests and to allow an initial configuration of the bank application.

### Password Hashing

For enhanced security, passwords are stored in the database as hashed values using **BCrypt**.

## Blockchain Integration

I created a small integration with a local Ethereum blockchain using Ganache and Truffle for deploying and interacting with a simple smart contract that exposes a simple public view function and does not consume gas. This function is used to check the Ether balance of a specified address, and this functionality is completely separate from the rest of the application logic, it has only been created to test the integration with the blockchain, and the application mantains all the other functionalities if we don't set up the blockchain integration.

### Steps to Set Up the Blockchain

1. **Run the Local Blockchain (Ganache)**
   - Start Ganache and click on Quickstart.
   - By default, Ganache will run on `localhost:7545`.

2. **Deploy the Contract Using Truffle**
     ```bash
     cd check-ether-contract
     truffle migrate
     ```

3. **Update `application.properties`**
   - After deployment, obtain the contract address and the private key of one of the accounts created in Ganache. 
   - Update the following properties to your `application.properties` file:
     ```properties
     blockchain.contract.address=<your_contract_address>
     blockchain.credentials.privateKey=<your_private_key>
     ```

4. **Launch the Application**
   - Now that the blockchain is running and the contract is deployed, launch the application:
     ```bash
     mvn spring-boot:run
     ```

---
## API

The API uses **JWT-based authentication**.

### Login

- **Endpoint**: `POST /login`
Retrieves a token for the specified user.
You can create first a new user or use the already created user: 

**Input parameters**
| parameter | type | example value | in | required
|--|--|--|--|--|
| username |string| santisr117 |query| true|
| password|string| 123123|query| true|

**Sample response**
```json
{

"username": "santisr117",

"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW50aXNyMTE3IiwiaWF0IjoxNzMzMjI3MDkxLCJleHAiOjE3MzMzMTM0OTF9.98pu6n2Sf0K0YEiQveIySLu7mwnoZFk7bUsx6MPm_L0"

}
```
### User

- **Endpoint**: `POST /user`

	Creates a new user.
	**Input parameters**
	| parameter | type | example value| in | required
	|--|--|--|--|--|
	| Authorization | JWT| Bearer + JWT Token | Header | true
	
	**Input Json**
	```json
	{

	"username": "santisr117",

	"password": "123123",

	"email": "santiagosr117@gmail.com"

	}
	```
	**Sample response**
	 `Http Status 201: Created`

- **Endpoint**: `GET /user/{id}`

	Get user info by id.
	**Input parameters**
	| parameter | type | example value| in | required
	|--|--|--|--|--|
	| Authorization | JWT| Bearer + JWT Token | Header | true
	| id | integer| 1 | Path | true
	
	**Sample response**
	 ```json
		{
	    "username": "santisr117",
	    "email": "santiagosr117@gmail.com",
	    "id": 1,
	    "wallets": [
	        {
	            "id": 1,
	            "name": "wallet1",
	            "balance": 115.00,
	            "transactions": [
	                {
	                    "id": 1,
	                    "description": "transaction 1",
	                    "date": "3/12/24 17:08",
	                    "amount": 15.00
	                }
	            ]
	        },
	        {
	            "id": 2,
	            "name": "wallet2",
	            "balance": 50.00,
	            "transactions": []
	        }
	    ]
	}
### Wallet

- **Endpoint**: `POST /wallet`

	Creates a new wallet.
	**Input parameters**
	| parameter | type | example value| in |
	|--|--|--|--|
	| Authorization | JWT| Bearer + JWT Token | Header 
	| userId | integer| 1 | Query 
	| name | string| wallet 3 | Query 
	
	**Sample response**
	 `Http Status 201: Created`
- **Endpoint**: `GET /wallet/{id}`

	Get wallet info by id.
	**Input parameters**
	| parameter | type | example value| in | required
	|--|--|--|--|--|
	| Authorization | JWT| Bearer + JWT Token | Header | true
	| id | integer| 1 | Path | true
	
	**Sample response**
	 ```json
		{
	    "id": 1,
	    "name": "wallet1",
	    "balance": 80.00,
	    "transactions": [
	        {
	            "id": 1,
	            "description": "tx1",
	            "date": "3/12/24 17:08",
	            "amount": 15.00
	        },
	        {
	            "id": 2,
	            "description": "Transfer to wallet 2",
	            "date": "3/12/24 17:16",
	            "amount": -50.00
	        },
	        {
	            "id": 4,
	            "description": "tx2",
	            "date": "3/12/24 17:16",
	            "amount": 15.00
	        }
	    ]
	}
- **Endpoint**: `POST /wallet/{id}/deposit`

	Makes a new deposit to a specified wallet id.
	**Input parameters**
	| parameter | type | example value| in | required
	|--|--|--|--|--|
	| Authorization | JWT| Bearer + JWT Token | Header | true
	| id | integer| 1 | Path | true
	| amount | number | 10 | Query  | true
	| description | string| new deposit | Query  | false
	
	**Sample response**
	 `Http Status 200: OK`
- **Endpoint**: `POST /wallet/{id}/transfer`

	Makes a transfer from a source wallet to a specified wallet id.
	**Input parameters**
	| parameter | type | example value| in | required
	|--|--|--|--|--|
	| Authorization | JWT| Bearer + JWT Token | Header  | true
	| id | integer| 1 | Path | true
	| amount | number | 10 | Query  | true
	| target id | integer | 2 | Query  | true
	
	**Sample response**
	 `Http Status 200: OK`
---
### Blockchain <a name="Blockchain"></a>

- **Endpoint**: `GET /blockchain/balance`

	Check the balance of a wallet address in the local blockchain.
	**Input parameters**
	| parameter | type | example value| in | required
	|--|--|--|--|--|
	| Authorization | JWT| Bearer + JWT Token | Header | true
	| walletAddress | string | 0x0c642236ac09e6a4402921b5a9e6ae2f0a49f00c | Query | true
	
	**Sample response**
	 ```json
		{
	    "balance": 100
	    }




