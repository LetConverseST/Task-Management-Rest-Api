# Task Management REST API

A beginner-to-intermediate level backend project built with Java, Spring Boot, MySQL, Spring Data JPA, and Maven. This project provides REST APIs to create, update, delete, complete, and filter tasks by status or priority.

The code follows a clean layered architecture:

- Controller layer for REST endpoints
- Service layer for business logic
- Repository layer for database access
- DTO layer for API request and response data
- Global exception handling for consistent error responses
- Validation for safe and clean input handling

## Technologies Used

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL
- Hibernate Validator
- Maven
- Postman

## Project Structure

```text
task-management-api/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── taskmanager/
        │           ├── TaskManagementApplication.java
        │           ├── config/
        │           ├── controller/
        │           │   └── TaskController.java
        │           ├── dto/
        │           │   └── TaskDTO.java
        │           ├── entity/
        │           │   ├── Task.java
        │           │   ├── TaskPriority.java
        │           │   └── TaskStatus.java
        │           ├── exception/
        │           │   ├── GlobalExceptionHandler.java
        │           │   └── ResourceNotFoundException.java
        │           ├── repository/
        │           │   └── TaskRepository.java
        │           └── service/
        │               ├── TaskService.java
        │               └── TaskServiceImpl.java
        └── resources/
            └── application.properties
```

## Features

- Create a new task
- Get all tasks
- Get a task by ID
- Update a task
- Delete a task
- Mark a task as completed
- Filter tasks by status
- Filter tasks by priority
- Input validation using Hibernate Validator
- Custom exception handling using `@ControllerAdvice`
- Automatic table creation using Spring Data JPA and Hibernate

## Task Fields

| Field | Type | Description |
| --- | --- | --- |
| `id` | Long | Auto-generated task ID |
| `title` | String | Task title |
| `description` | String | Task description |
| `status` | Enum | `PENDING`, `IN_PROGRESS`, `COMPLETED` |
| `priority` | Enum | `LOW`, `MEDIUM`, `HIGH` |
| `dueDate` | LocalDate | Due date of the task |
| `createdAt` | LocalDateTime | Auto-generated creation timestamp |

## Database Setup

Create a MySQL database:

```sql
CREATE DATABASE task_management_db;
```

The application is also configured with `createDatabaseIfNotExist=true`, so it can create the database automatically if the MySQL user has permission.

Update your MySQL username and password in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

Hibernate will automatically create/update the `tasks` table because this property is enabled:

```properties
spring.jpa.hibernate.ddl-auto=update
```

## How to Run

Open the project in IntelliJ IDEA, wait for Maven dependencies to load, then run:

```text
TaskManagementApplication.java
```

Or run from terminal:

```bash
mvn spring-boot:run
```

The server will start at:

```text
http://localhost:8080
```

## API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/tasks` | Create a task |
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get a task by ID |
| PUT | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |
| PATCH | `/api/tasks/{id}/complete` | Mark task as completed |
| GET | `/api/tasks/status/{status}` | Filter tasks by status |
| GET | `/api/tasks/priority/{priority}` | Filter tasks by priority |

## Sample JSON Requests

### Create Task

POST `http://localhost:8080/api/tasks`

```json
{
  "title": "Complete DBMS assignment",
  "description": "Finish normalization questions and submit before deadline",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2026-06-15"
}
```

Sample response:

```json
{
  "id": 1,
  "title": "Complete DBMS assignment",
  "description": "Finish normalization questions and submit before deadline",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2026-06-15",
  "createdAt": "2026-05-26T10:30:00"
}
```

### Update Task

PUT `http://localhost:8080/api/tasks/1`

```json
{
  "title": "Complete DBMS assignment and notes",
  "description": "Finish normalization questions, prepare notes, and submit before deadline",
  "status": "IN_PROGRESS",
  "priority": "MEDIUM",
  "dueDate": "2026-06-20"
}
```

### Mark Task as Completed

PATCH `http://localhost:8080/api/tasks/1/complete`

No request body is required.

### Filter by Status

GET `http://localhost:8080/api/tasks/status/PENDING`

Valid status values:

- `PENDING`
- `IN_PROGRESS`
- `COMPLETED`

### Filter by Priority

GET `http://localhost:8080/api/tasks/priority/HIGH`

Valid priority values:

- `LOW`
- `MEDIUM`
- `HIGH`

## Validation Rules

- `title` is required
- `title` must be between 3 and 100 characters
- `description` cannot exceed 500 characters
- `priority` is required
- `dueDate` is required
- `dueDate` must be today or a future date

Invalid request example:

```json
{
  "title": "",
  "description": "Invalid task because title is blank",
  "priority": "HIGH",
  "dueDate": "2024-01-01"
}
```

Sample validation response:

```json
{
  "timestamp": "2026-05-26T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": {
    "title": "Title is required",
    "dueDate": "Due date must be today or a future date"
  }
}
```

## Postman Testing Guide

1. Start the Spring Boot application.
2. Open Postman.
3. Set base URL as `http://localhost:8080`.
4. Use `Body > raw > JSON` for POST and PUT requests.
5. Add this header for JSON requests:

```text
Content-Type: application/json
```

Recommended testing order:

1. Create a task using `POST /api/tasks`.
2. Fetch all tasks using `GET /api/tasks`.
3. Fetch one task using `GET /api/tasks/{id}`.
4. Update the task using `PUT /api/tasks/{id}`.
5. Mark it completed using `PATCH /api/tasks/{id}/complete`.
6. Filter tasks using status and priority endpoints.
7. Delete the task using `DELETE /api/tasks/{id}`.

## Example cURL Commands

Create task:

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Prepare Java viva\",\"description\":\"Revise Spring Boot, JPA, and REST API concepts\",\"status\":\"PENDING\",\"priority\":\"HIGH\",\"dueDate\":\"2026-06-10\"}"
```

Get all tasks:

```bash
curl http://localhost:8080/api/tasks
```

Mark task completed:

```bash
curl -X PATCH http://localhost:8080/api/tasks/1/complete
```

## Interview Talking Points

- Used layered architecture to separate controller, service, repository, entity, DTO, and exception handling responsibilities.
- Used Spring Data JPA to reduce boilerplate database code.
- Used enum fields for controlled `status` and `priority` values.
- Used DTO validation to prevent invalid API input.
- Used global exception handling to return consistent error responses.
- Used RESTful endpoint naming and proper HTTP methods.
- Used MySQL as a persistent relational database.

