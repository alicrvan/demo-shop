# demo-shop
An e-commerce shop project to demonstrate my skills. The project's architecture is based on microservices using Spring Boot. Below are the key components of the project:

1. **Product Management Microservice**
   - Responsible for managing products including adding, updating, deleting, and retrieving product information (**JOSN/Validation/CRUD Operations**).
   - Utilizes its own database for storing product-related data (**Spring Data JPA/Hibernate with MySQL/Docker**).
   - Implements security measures and Role-based access control (RBAC) to control access (**Spring Security/NoSQL/MongoD**).

2. **User Management Microservice**
   - Handles user-related functionality including user registration, authentication, and user profile management.
   - Maintains a dedicated database for storing user data securely (**NoSQL/MongoDB**).

3. **Management Portal**
   - A web-based portal designed for employees and administrators.
   - Authorized users can access administrative functions, such as managing products, orders, and users.

4. **User Portal**
   - A user-friendly website for customers.
   - Features include browsing the product catalog, adding items to the cart, making purchases, and managing user profiles.

