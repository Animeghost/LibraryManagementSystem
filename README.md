# Library Management System - Backend & Frontend

A comprehensive library management solution featuring a Spring Boot REST API backend and a JavaFX-based desktop frontend, implementing full CRUD operations for library resource management.

## Project Structure

```
LibraryManagementSystem_1.0/
├── LibraryManagementBackend/    # Spring Boot REST API
├── LibraryManagementFrontend/   # JavaFX Desktop Application
└── README.md
```


### Backend (`LibraryManagementBackend`)

A Spring Boot-based REST API for managing library operations.

**Technologies:**
- **Java** 17 SDK
- **Spring Boot** - Web Framework
- **Spring Data JPA** - Data Access Layer
- **Jakarta EE** - Enterprise Java APIs
- **Maven** - Build and Dependency Management
- **Lombok** - Reducing boilerplate code

**Key Features:**
- RESTful API endpoints for CRUD operations
- Database persistence with JPA
- Request/Response handling
- Maven build configuration

**Build & Run:**
```shell script
cd LibraryManagementBackend
mvn clean install
mvn spring-boot:run
```


---

### Frontend (`LibraryManagementFrontend`)

A JavaFX desktop application providing a user-friendly interface to interact with the library management system.

**Technologies:**
- **JavaFX** - Desktop GUI Framework
- **Maven** - Build and Dependency Management
- **Java** 17 SDK
- **SceneBuilder** - JavaFX Scene Builder for UI design and layout

**Key Features:**
- Desktop graphical user interface
- Integration with backend REST API
- Complete CRUD operations interface
- Cross-platform compatibility

**Build & Run:**
```shell script
cd LibraryManagementFrontend
mvn clean install
mvn javafx:run
```


---

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Maven 3.6+

### Setup Instructions

1. **Clone or extract the project**
```shell script
cd LibraryManagementSystem_1.0
```


2. **Build and Run Backend**
```shell script
cd LibraryManagementBackend
   mvn clean install
   mvn spring-boot:run
```

Backend will be available at `http://localhost:8080` (or configured port)

3. **Build and Run Frontend**
```shell script
cd LibraryManagementFrontend
   mvn clean install
   mvn javafx:run
```


---

## Features

- **Create** - Add new library resources
- **Read** - View and search library items
- **Update** - Modify existing resource information
- **Delete** - Remove resources from the system

---

## Notes

- Ensure the backend is running before launching the frontend
- Configure database connection settings in the backend as needed
- Check individual `pom.xml` files for detailed dependency information

---

Feel free to ask if you need more details about any specific component!