# Service Layer Implementation

This section describes the steps to implement the service layer in your Spring Boot application, focusing on translating models, handling CRUD operations, and ensuring clean separation of concerns.

---

## Steps to Build the Service Layer

### 1. Create the `services` Package

- **Location:** `src/main/java/<your-base-package>/services`
- **Class:** `CustomerService`

---

### 2. Annotate the Service

- Use the `@Service` annotation to designate `CustomerService` as a Spring-managed service component.

---

### 3. Dependency Injection

- Inject the `CustomerRepository` into `CustomerService`.
- Make the repository field `final` for immutability.
- Add a constructor for dependency injection:

  ```java
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
      this.customerRepository = customerRepository;
  }
  ```

---

### 4. Translation Methods

#### `translateWebToDb(Customer customer)`

- **Purpose:** Converts `Customer` (API model) to `CustomerEntity` (DB entity) using simple field mapping.
- **Why?** Ensures consistency between API models and database entities while keeping them separate.

#### Example:

```java
private CustomerEntity translateWebToDb(Customer customer) {
    return new CustomerEntity(
        customer.getCustomerId(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getEmailAddress(),
        customer.getPhoneNumber()
    );
}
```

---

#### `translateDbToWeb(CustomerEntity entity)`

- **Purpose:** Converts `CustomerEntity` (DB entity) to `Customer` (API model) using `AllArgsConstructor` for simplicity.
- **Why?** Allows the API to return models tailored for client consumption without exposing internal DB structure.

#### Example:

```java
private Customer translateDbToWeb(CustomerEntity entity) {
    return new Customer(
        entity.getCustomerId(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getEmailAddress(),
        entity.getPhoneNumber()
    );
}
```

---

### 5. CRUD Methods

#### `getAllCustomers(String filterEmail)`

- **Purpose:** Retrieves all customers or filters them by email.
- **Implementation:**
  - If `filterEmail` is provided, filter customers by email.
  - Otherwise, retrieve all customers from the database.
  - Map results using `translateDbToWeb`.

#### Example:

```java
public List<Customer> getAllCustomers(String filterEmail) {
    if (filterEmail != null && !filterEmail.isEmpty()) {
        return customerRepository.findByEmail(filterEmail).stream()
                .map(this::translateDbToWeb)
                .collect(Collectors.toList());
    }
    return customerRepository.findAll().stream()
            .map(this::translateDbToWeb)
            .collect(Collectors.toList());
}
```

---

#### `getCustomer(Long id)`

- **Purpose:** Retrieves a customer by their ID.
- **Implementation:**
  - Fetch customer using `findById()`.
  - Throw `NotFoundException` if the customer does not exist.
  - Return the translated `Customer`.

#### Example:

```java
public Customer getCustomer(Long id) {
    return customerRepository.findById(id)
            .map(this::translateDbToWeb)
            .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + id));
}
```

---

#### `createOrUpdate(Customer customer)`

- **Purpose:** Creates a new customer or updates an existing one.
- **Implementation:**
  - Translate `Customer` to `CustomerEntity`.
  - Save the entity using `save()` and return the translated `Customer`.

#### Example:

```java
public Customer createOrUpdate(Customer customer) {
    CustomerEntity entity = translateWebToDb(customer);
    return translateDbToWeb(customerRepository.save(entity));
}
```

---

#### `deleteCustomer(Long id)`

- **Purpose:** Deletes a customer by their ID.
- **Implementation:**
  - Use `deleteById()` to remove the customer from the database.

#### Example:

```java
public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
}
```

---

## Key Features

### **Service Layer**

- **Clean Separation:** Isolates business logic from database operations and the web layer.
- **Reusability:** Translation methods ensure consistency across CRUD operations.

### **Error Handling**

- Uses `NotFoundException` to handle missing resources effectively.

### **Extensibility**

- Easy to add more business logic or additional features in the future.

## Next Step

[Next Step: Entity, Models, Repo and Service layers](EMRSLayer.md)
