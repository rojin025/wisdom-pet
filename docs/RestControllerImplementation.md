# CustomerRestController Implementation

This document provides a structured guide for creating a `CustomerRestController` to expose a RESTful API for the Customer domain in a Spring Boot application.

---

## Steps to Build the REST API

### 1. Create the REST Package

- **Location:** `src/main/java/<your-base-package>/web/rest`
- **Class:** `CustomerRestController`

---

### 2. Annotate the Controller

- Use `@RestController` to define the class as a REST API controller.
- Add `@RequestMapping("/api/customers")` to set the base route for the API.

---

### 3. Inject CustomerService

- Use `private final CustomerService` for dependency injection.
- Use constructor injection to initialize the service:

  ```java
  private final CustomerService customerService;

  public CustomerRestController(CustomerService customerService) {
      this.customerService = customerService;
  }
  ```

---

## Methods in the Controller

### 1. GET: Retrieve All Customers

- **Endpoint:** `/api/customers`
- **Annotation:** `@GetMapping`
- **Parameters:**
  - `@RequestParam(name = "email", required = false)` to filter customers by email.
- **Implementation:**
  ```java
  @GetMapping
  public List<Customer> getAllCustomers(@RequestParam(name = "email", required = false) String email) {
      return customerService.getAllCustomers(email);
  }
  ```

---

### 2. POST: Create a New Customer

- **Endpoint:** `/api/customers`
- **Annotation:** `@PostMapping`
- **Request Body:** `Customer`
- **Implementation:**
  ```java
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer createCustomer(@RequestBody Customer customer) {
      return customerService.createOrUpdate(customer);
  }
  ```

---

### 3. GET: Retrieve a Customer by ID

- **Endpoint:** `/api/customers/{id}`
- **Annotation:** `@GetMapping("/{id}")`
- **Path Variable:** `Long id`
- **Implementation:**
  ```java
  @GetMapping("/{id}")
  public Customer getCustomer(@PathVariable Long id) {
      return customerService.getCustomer(id);
  }
  ```

---

### 4. PUT: Update an Existing Customer

- **Endpoint:** `/api/customers/{id}`
- **Annotation:** `@PutMapping("/{id}")`
- **Request Body:** `Customer`
- **Validation:** Ensure the `id` in the path matches `customer.getCustomerId()`.
- **Implementation:**
  ```java
  @PutMapping("/{id}")
  public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
      if (!id.equals(customer.getCustomerId())) {
          throw new BadRequestException("ID in path and request body must match.");
      }
      return customerService.createOrUpdate(customer);
  }
  ```

---

### 5. DELETE: Remove a Customer by ID

- **Endpoint:** `/api/customers/{id}`
- **Annotation:** `@DeleteMapping("/{id}")`
- **Path Variable:** `Long id`
- **Implementation:**
  ```java
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteCustomer(@PathVariable Long id) {
      customerService.deleteCustomer(id);
  }
  ```

---

## Final Code for CustomerRestController

```java
package <your-base-package>.web.rest;

import <your-base-package>.services.CustomerService;
import <your-base-package>.web.models.Customer;
import <your-base-package>.web.errors.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam(name = "email", required = false) String email) {
        return customerService.getAllCustomers(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createOrUpdate(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (!id.equals(customer.getCustomerId())) {
            throw new BadRequestException("ID in path and request body must match.");
        }
        return customerService.createOrUpdate(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
```

---

## Testing the API

### Run the Application

- Use your IDE or build tool to start the application:
  ```bash
  ./mvnw spring-boot:run
  ```

### Test Endpoints

- Use a browser, Postman, or curl to test the following endpoints:
  - `GET: localhost:8080/api/customers`
  - `GET: localhost:8080/api/customers/{id}`
  - `POST: localhost:8080/api/customers`
  - `PUT: localhost:8080/api/customers/{id}`
  - `DELETE: localhost:8080/api/customers/{id}`

`- Happy Coding :)`
