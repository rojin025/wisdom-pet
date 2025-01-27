# Error handling and Model creation

This guide outlines the initial steps for setting up a Spring Boot project, focusing on error handling and model creation for a web service.

[Back to Main README](../README.md)

---

## Steps to Set Up the Project

### 1. Create a `web.errors` Package

**Purpose:** Handle exceptions in a structured way for the web service.

**Classes to Add:**

- **`NotFoundException`:**

  - Annotate with `@ResponseStatus(HttpStatus.NOT_FOUND)`.
  - Extend `RuntimeException` and override its first four methods.

- **`BadRequestException`:**
  - Annotate with `@ResponseStatus(HttpStatus.BAD_REQUEST)`.
  - Extend `RuntimeException` and override its first four methods.

---

### 2. Create a `web.models` Package

**Purpose:** Define the data structure exposed via the API, separate from the database entity.

**Class to Add:**

- **`Customer`:**
  - Use Lombok annotations to reduce boilerplate:
    - `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`.
  - Annotate with `@JsonIgnoreProperties(ignoreUnknown = true)` to handle extra JSON properties gracefully.
  - Define fields:
    ```java
    public Long customerId;
    public String firstName;
    public String lastName;
    public String emailAddress;
    public String phoneNumber;
    ```

---

## Rationale for this Setup

### Error Handling:

- Centralizes HTTP error responses (e.g., 404 Not Found, 400 Bad Request) for easier debugging and improved client interaction.

### Model Design:

- Separating API models from database entities ensures flexibility and clarity.
- The `Customer` model controls JSON output, simplifying API documentation and usability.

---

## Next Step

- Build a **service layer** to handle data access and wrap it for web services consumption.

[Next Step: Service Layer Implementation](ServiceLayer.md)

`Happy Coding :)`
