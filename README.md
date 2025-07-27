SecuraCloud: Secure Cloud File Storage
======================================

SecuraCloud is a robust and secure backend application for cloud-based file management. Built with Spring Boot, it provides a RESTful API for user authentication, role-based access, and seamless file storage integration with AWS S3.

* * * * *

Key Features
------------

-   **Secure JWT Authentication**: End-to-end security with JSON Web Tokens (JWT) for stateless and secure API access.

-   **Role-Based Access Control (RBAC)**: Differentiates permissions for `USER` and `ADMIN` roles, ensuring that users can only access authorized resources.

-   **AWS S3 Integration**: Manages file uploads, storage, and deletion through integration with Amazon S3, providing a scalable and reliable storage solution.

-   **RESTful API**: A comprehensive set of endpoints for user registration, login, file upload/download, and administrative tasks.

-   **Database Integration**: Uses Spring Data JPA and Hibernate to interact with a MySQL database for managing user and file metadata.

-   **Integration Testing**: Includes integration tests to ensure the reliability and correctness of the API endpoints.

* * * * *

Tech Stack
----------

-   **Backend**: Spring Boot, Spring Security, Spring Data JPA

-   **Database**: MySQL

-   **File Storage**: AWS S3

-   **Authentication**: JSON Web Tokens (jjwt)

-   **Build Tool**: Maven

* * * * *

Prerequisites
-------------

Before you begin, ensure you have the following installed:

-   Java 17 or higher

-   Apache Maven

-   MySQL Server

-   An AWS account with an S3 bucket and access credentials

* * * * *

Getting Started
---------------

To get the project up and running locally, follow these steps:

### 1\. Clone the Repository

Bash

```
git clone https://github.com/your-username/securacloud.git
cd securacloud

```

### 2\. Configure Environment Variables

This project uses environment variables to handle sensitive credentials securely. Create a `.env` file in the root directory of the project (or set system-wide environment variables).

Bash

```
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/cloud_storage
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password

# AWS S3 Credentials
AWS_ACCESS_KEY_ID=your_aws_access_key
AWS_SECRET_KEY=your_aws_secret_key
AWS_S3_BUCKET_NAME=your-s3-bucket-name
AWS_REGION=your-aws-region

# JWT Secret Key
JWT_SECRET=your-super-strong-and-long-jwt-secret

```

### 3\. Set Up the Database

Make sure your MySQL server is running and create a database named `cloud_storage`. Spring Boot and Hibernate will automatically create the necessary tables when the application starts.

### 4\. Build and Run the Application

You can build and run the project using the included Maven wrapper.

Bash

```
# On Linux or macOS
./mvnw spring-boot:run

# On Windows
./mvnw.cmd spring-boot:run

```

The application will start on `http://localhost:8080`.

* * * * *

API Documentation
-----------------

The following are the primary endpoints provided by the API.

### Authentication

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/api/auth/register` | Registers a new user with the default `USER` role. |
| `POST` | `/api/auth/login` | Authenticates a user and returns a JWT. |



### File Management

| Method | Endpoint | Roles | Description |
| --- | --- | --- | --- |
| `POST` | `/api/files/upload` | `USER`, `ADMIN` | Uploads a file to AWS S3 and saves its metadata. |
| `DELETE` | `/api/files/delete/{fileName}` | `ADMIN` | Deletes a file from S3 and its metadata from the database. |
| `GET` | `/api/files/metadata` | `ADMIN` | Retrieves metadata for all files. |
| `GET` | `/api/files/metadata/{id}` | `ADMIN` | Retrieves metadata for a specific file by its ID. |
| `DELETE` | `/api/files/metadata/delete/{id}` | `ADMIN` | Deletes a file's metadata from the database. |



### Admin

| Method | Endpoint | Roles | Description |
| --- | --- | --- | --- |
| `GET` | `/api/admin/users` | `ADMIN` | Retrieves a list of all registered users. |
| `POST` | `/api/admin/add-user` | `ADMIN` | Adds a new user. |
| `PUT` | `/api/admin/assign-role` | `ADMIN` | Assigns a new role to a user. |
