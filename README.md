# Task Management REST API

A backend REST API project built using Java, Spring Boot, MySQL, Spring Data JPA, and Maven.  
The application provides APIs for task creation, management, filtering, and status tracking while following clean layered architecture and RESTful design principles.

---

## Features

- Create, update, and delete tasks
- Get all tasks or fetch by ID
- Mark tasks as completed
- Filter tasks by status and priority
- Input validation using Hibernate Validator
- Global exception handling using `@ControllerAdvice`
- Layered architecture implementation
- Automatic database table creation using JPA/Hibernate

---

## Technologies Used

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL
- Hibernate Validator
- Maven
- Postman

---

## Project Structure

```text
task-management-api/
│
├── src/main/java/com/taskmanager/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── dto/
│   ├── exception/
│   └── TaskManagementApplication.java
│
├── src/main/resources/
│   └── application.properties
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/tasks` | Create task |
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| PUT | `/api/tasks/{id}` | Update task |
| DELETE | `/api/tasks/{id}` | Delete task |
| PATCH | `/api/tasks/{id}/complete` | Mark task as completed |
| GET | `/api/tasks/status/{status}` | Filter by status |
| GET | `/api/tasks/priority/{priority}` | Filter by priority |

---

## Task Fields

| Field | Type |
|------|------|
| id | Long |
| title | String |
| description | String |
| status | Enum |
| priority | Enum |
| dueDate | LocalDate |
| createdAt | LocalDateTime |

### Status Values

- `PENDING`
- `IN_PROGRESS`
- `COMPLETED`

### Priority Values

- `LOW`
- `MEDIUM`
- `HIGH`

---

## Database Setup

Create database in MySQL:

```sql
CREATE DATABASE task_management_db;
```

Update database credentials in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## Run the Application

### Using IntelliJ IDEA

Open the project and run:

```text
TaskManagementApplication.java
```

### Using Terminal

```bash
mvn spring-boot:run
```

Application runs on:

```text
http://localhost:8080
```

---

## Sample Request

### Create Task

```http
POST /api/tasks
```

```json
{
  "title": "Complete Spring Boot Project",
  "description": "Build and test REST API project",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2026-06-10"
}
```

---

## Validation Rules

- Title is required
- Title must be between 3–100 characters
- Description cannot exceed 500 characters
- Due date must be present or future date
- Priority is required

---

## Interview Talking Points

- Implemented layered architecture using Controller → Service → Repository pattern
- Used Spring Data JPA for database operations
- Implemented RESTful APIs using proper HTTP methods
- Used DTO validation for safe API input handling
- Implemented centralized exception handling
- Integrated MySQL with Hibernate/JPA
- Tested APIs using Postman

---

## Future Improvements

- JWT Authentication & Authorization
- Docker deployment
- Pagination & sorting
- Swagger/OpenAPI documentation
- Unit and integration testing

---

## Author

Shivam Tiwari