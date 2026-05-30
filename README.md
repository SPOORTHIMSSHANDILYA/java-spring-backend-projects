# Library Management System API

A comprehensive Spring Boot REST API for managing a library's operations including members, books, authors, and borrow records.

## 📋 Project Overview

The Library Management System is a robust backend API built with Spring Boot that enables efficient management of:
- **Members** - Library member registration and management
- **Books** - Book catalog and inventory management
- **Authors** - Author information and their associated books
- **Borrow Records** - Tracking of book borrowing and returns

## 🛠️ Technology Stack

- **Framework**: Spring Boot 4.0.4
- **Language**: Java 21
- **Database**: MySQL
- **ORM**: Hibernate (Spring Data JPA)
- **Data Mapping**: MapStruct 1.5.5
- **Validation**: Jakarta Validation
- **Lombok**: For boilerplate code reduction
- **API Documentation**: Springdoc OpenAPI (Swagger UI)
- **Build Tool**: Maven

## 📦 Key Dependencies

```xml
<!-- Core Spring Boot Dependencies -->
spring-boot-starter-web
spring-boot-starter-data-jpa

<!-- Database -->
mysql-connector-j

<!-- Code Generation & Mapping -->
lombok
mapstruct

<!-- API Documentation -->
springdoc-openapi-starter-webmvc-ui (v3.0.3)

<!-- Custom Library -->
common-lib (v2026.05.05)
```

## 🏗️ Project Architecture

```
libraryManagement/
├── controllers/          # REST API endpoints
│   ├── MemberController
│   ├── BookController
│   ├── AuthorController
│   └── BorrowController
├── entities/            # JPA entities (database models)
│   ├── Member
│   ├── Book
│   ├── Author
│   └── BorrowRecord
├── services/            # Business logic layer
├── repository/          # Data access layer (JPA Repositories)
├── models/              # DTOs (Request/Response objects)
├── mapper/              # MapStruct mappers (Entity ↔ DTO conversion)
├── validators/          # Custom validation annotations
├── exceptions/          # Global exception handling
└── projections/         # Query projections
```

## 🔌 API Endpoints

### Member Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/members` | Get all members (paginated) |
| GET | `/api/members/{id}` | Get member by ID |
| GET | `/api/members/{id}/borrows` | Get member's borrow history |
| POST | `/api/members` | Create new member |
| PUT | `/api/members/{id}` | Update member |
| DELETE | `/api/members/{id}` | Delete member |

### Book Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books (paginated) |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books/search` | Search books by title, genre, or author |
| GET | `/api/books/available` | Get available books |
| POST | `/api/books` | Add new book |
| PUT | `/api/books/{id}` | Update book |
| DELETE | `/api/books/{id}` | Delete book |

### Author Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/authors` | Get all authors |
| GET | `/api/authors/{id}` | Get author by ID |
| GET | `/api/authors/{id}/books` | Get books by author |
| POST | `/api/authors` | Create new author |
| PUT | `/api/authors/{id}` | Update author |
| DELETE | `/api/authors/{id}` | Delete author |

### Borrow Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/borrows` | Get all borrow records |
| POST | `/api/borrows` | Create new borrow record |
| PUT | `/api/borrows/{id}` | Return a book |

## ✨ Key Features

### 1. **Global Exception Handling**
- Centralized exception handling via `GlobalExceptionHandler`
- Custom `ResourceNotFoundException` with 404 status
- Validation error cleanup with detailed error messages
- Standard error response format

### 2. **Request Validation**
- Built-in JSR-303 validation annotations
- Custom `@UniqueEmail` validator for members
- Automatic validation on request bodies with `@Valid`

### 3. **Pagination & Sorting**
- Paginated endpoints for members and books
- Configurable sorting by fields and direction
- PagedResponseDTO wrapper for consistent pagination

### 4. **Data Mapping**
- MapStruct for efficient Entity ↔ DTO mapping
- Type-safe object conversion
- Reduced boilerplate code

### 5. **Query Optimization**
- Native SQL queries with Java 17 text blocks
- Query projections for optimized database reads
- LEFT JOINs to include related data efficiently

### 6. **Unique Constraints**
- Email uniqueness validation (database + application level)
- Prevents duplicate member registrations

## 🔧 Configuration

### Database Setup

Update `application.properties`:

```properties
spring.application.name=libraryManagement

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/library_management
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Running the Application

```bash
# Build the project
mvn clean build

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📖 API Documentation

Swagger UI is available at:
```
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON documentation:
```
http://localhost:8080/v3/api-docs
```

## 📝 Request/Response Format

### Success Response Example

```json
{
    "code": 200,
    "message": "Success",
    "data": {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "john@example.com",
        "phone": "9999999999",
        "membershipDate": "2026-05-10",
        "active": true,
        "activeBorrows": 2
    },
    "status": "Success"
}
```

### Error Response Example

```json
{
    "code": 404,
    "message": "Member not found",
    "data": "Member with ID 1000 not found in database",
    "status": "Error"
}
```

## 🚀 Getting Started

### Prerequisites
- Java 21+
- MySQL 8.0+
- Maven 3.6+

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd libraryManagement
   ```

2. **Create MySQL Database**
   ```sql
   CREATE DATABASE library_management;
   ```

3. **Configure Database Credentials**
   - Update `application.properties` with your MySQL credentials

4. **Build and Run**
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

5. **Access the API**
   - Base URL: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

## 📚 Sample API Usage

### Create a New Member
```bash
POST /api/members
Content-Type: application/json

{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phone": "9999999999"
}
```

### Get Members with Pagination
```bash
GET /api/members?page=0&size=10&sortBy=id&direction=asc
```

### Search Books
```bash
GET /api/books/search?title=Java&genre=Programming&authorId=1
```

## 🔐 Validation Rules

### Member
- `firstName`: Required, must not be blank
- `lastName`: Required, must not be blank
- `email`: Required, must be valid email format, must be unique
- `phone`: Optional

### Book
- `title`: Required
- `isbn`: Required, unique
- `genre`: Required
- `author`: Required

### Author
- `firstName`: Required
- `lastName`: Required
- `email`: Optional

## 🐛 Error Handling

The API returns appropriate HTTP status codes:

| Status | Description |
|--------|-------------|
| 200 | OK - Request successful |
| 400 | Bad Request - Validation failed |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error |

All errors follow the standard error response format with clear messages.

## 📖 Database Schema

### Core Tables
- **members** - Library member information
- **books** - Book catalog
- **authors** - Author details
- **borrow_records** - Borrowing transactions with status tracking

## 🤝 Dependencies Between Services

- **BorrowService** depends on MemberService and BookService
- **BookService** depends on AuthorService
- All services use their respective repositories

## 📄 License

This project is part of Spring Boot Udemy course.

## 👤 Author

Created as part of Spring Boot learning and development.

---

**Last Updated**: May 2026
```
