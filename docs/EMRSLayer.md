# EMRS Implementation Steps

This section provides detailed guidelines to implement additional entities in the project, including database entities, web models, repositories, and service classes. It also includes tips for resolving naming conflicts and ensuring smooth integration.

---

## Implemention Steps

### 1. Extract Entity Details

- Open the `schema.sql` file located in `src/main/resources` to review the table structures for the additional entities.
- Identify the fields and relationships defined for each table.

---

### 2. Create Entity Classes

- Define Java classes representing the entities in the `data` package (e.g., `OrderEntity`, `ProductEntity`, `InvoiceEntity`).
- Use the `@Entity` annotation to define each class as a database entity.
- Map fields to database columns using:
  - `@Id` for the primary key.
  - `@GeneratedValue` for auto-generated IDs.
  - `@Column` for database field mapping.

#### Example:

```java
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    // Additional fields and relationships
}
```

---

### 3. Create Web Models

- Define corresponding classes in the `web.models` package (e.g., `Order`, `Product`, `Invoice`).
- Use Lombok annotations to simplify boilerplate code:
  - `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`.
- Annotate each class with `@JsonIgnoreProperties(ignoreUnknown = true)` to handle unexpected JSON properties gracefully.

#### Example:

```java
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private String orderNumber;

    // Additional fields
}
```

---

### 4. Create Repository Interfaces

- Create an interface for each entity in the `data` package (e.g., `OrderRepository`, `ProductRepository`, `InvoiceRepository`).
- Extend `JpaRepository<EntityName, Long>` to leverage Spring Data JPA functionalities.

#### Example:

```java
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
```

---

### 5. Create Service Classes

- Implement service classes for each entity in the `services` package (e.g., `OrderService`, `ProductService`, `InvoiceService`).
- Annotate each class with `@Service` or `@Component` as appropriate.
- Implement translation methods:
  - `translateWebToDb()`: Converts the web model to a database entity.
  - `translateDbToWeb()`: Converts the database entity to a web model.
- Develop CRUD methods similar to those in `CustomerService`.

#### Example:

```java
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private OrderEntity translateWebToDb(Order order) {
        return new OrderEntity(
            order.getId(),
            order.getOrderNumber()
            // Additional fields
        );
    }

    private Order translateDbToWeb(OrderEntity entity) {
        return new Order(
            entity.getId(),
            entity.getOrderNumber()
            // Additional fields
        );
    }

    // CRUD methods
}
```

---

## Tips for Resolving Name Conflicts

- Use `@Component` for service classes if `@Service` causes naming conflicts.
- Alternatively, use fully qualified names like `@org.springframework.stereotype.Service` to avoid ambiguity.

---

## Deliverables

1. **Three entity classes:**

   - `OrderEntity`
   - `ProductEntity`
   - `InvoiceEntity`

2. **Three web models:**

   - `Order`
   - `Product`
   - `Invoice`

3. **Three repository interfaces:**

   - `OrderRepository`
   - `ProductRepository`
   - `InvoiceRepository`

4. **Three service classes:**
   - `OrderService`
   - `ProductService`
   - `InvoiceService`
