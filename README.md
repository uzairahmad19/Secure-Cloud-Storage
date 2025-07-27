# SecuraCloud

SecuraCloud is a robust Spring Boot backend application for secure, cloud-based file management. It leverages JWT authentication, role-based access control, and AWS S3 integration to provide a scalable solution for uploading, retrieving, and deleting files.

## Features

- **Secure Authentication:** JWT-based authentication for secure API access.
- **Role-Based Access Control:** Differentiates permissions for `USER` and `ADMIN` roles.
- **Cloud File Management:** Seamless integration with AWS S3 for file storage and metadata management.
- **RESTful API:** Comprehensive endpoints for user registration, login, and file operations.
- **Database Integration:** Uses MySQL with Spring Data JPA to handle all backend data operations.

## Tech Stack

- **Backend:** Spring Boot, Spring Security, Spring Data JPA
- **Database:** MySQL
- **File Storage:** AWS S3 (or LocalStack for local development)
- **Authentication:** JWT (JSON Web Tokens)
- **Build Tool:** Maven

### Prerequisites

- Java 17
- Maven 3.6+
- MySQL Database
- AWS S3 Account (or LocalStack for local testing)
- Git
