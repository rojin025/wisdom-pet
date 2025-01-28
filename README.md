# Wisdom Pet

A Spring Boot application integrated with a React frontend for serving a modern web UI.

# Table of Contents

[Setup Instructions](#setup-instructions)

# Project Documentation

Welcome to the documentation for Wisdom Pet. Below is a guide to the different sections of the project:

- [Error Handling](docs/ErrorHandling.md)
- [Service Layer Implementation](docs/ServiceLayer.md)
- [Entity, Models, Repo and Service layers](docs/EMRSLayer.md)
- [REST Controller Implementation](docs/RestControllerImplementation.md)
- [Deployment Instructions](docs/Deployment.md)

## Project Structure

- Backend: Spring Boot (Java)
- Frontend: React (JavaScript)

## Prerequisites

Ensure the following are installed on your system:

- Java 17+
- Node.js v19.6.1+
- Maven 3.6+

# Setup instructions

1. Clone the Repository

```bash
git clone <repository-url>
cd wisdom-pet
```

2. Install Dependencies
   Navigate to the React frontend directory and install its dependencies:

```bash
cd src/ui
npm install
```

Return to the project root directory:

```bash
cd ../../
```

3. Build the Project
   Use Maven to build both the backend and frontend. The `frontend-maven-plugin` will automate the React build process and copy the files to the Spring Boot `static` directory.

```bash
mvn clean package
```

4. Run the Application
   Execute the generated `.jar` file from the `target` directory:

```bash
java -jar target/wisdom-pet-0.0.1-SNAPSHOT.jar
```

5. Access the Application
   Once the application starts, it will be available at:

```bash
http://localhost:8080
```

6. Build Process Details

The following steps are automated by Maven:

- Node.js Installation: Ensures the correct Node.js version is installed using the `frontend-maven-plugin`.
- Frontend Build: Runs `npm install` and `npm run build` for the React application.
- File Integration: Copies React build files into the Spring Boot `static` directory.

## Troubleshooting

- Missing `.jar` File: Ensure the `mvn clean package` command completes without errors.
- Port Issues: Check if another service is using port `8080`. Change the port in `application.properties` if needed.
- React Errors: Debug React issues in the `src/ui` directory by running:

```bash
npm start
```

Next Step: [Error Handling](docs/ErrorHandling.md)

`Happy Coding :)`
