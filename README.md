Spring Boot JWT Authentication Setup

This README provides a comprehensive guide to setting up JWT (JSON Web Token) authentication in a Spring Boot application, emphasizing the importance of security in protecting access to resources. JWT authentication introduces a robust security layer, ensuring that only authenticated users can access protected resources.

Dependencies

To successfully integrate JWT authentication into a Spring Boot application, several Maven dependencies are essential. These dependencies encompass the Spring Boot Starter Security and the JJWT library, along with its API, Implementation, and Jackson Integration modules.

Spring Boot Starter Security

The Spring Boot Starter Security serves as the cornerstone of our security infrastructure. It seamlessly integrates Spring Security into our Spring Boot application, offering a wide array of authentication, protection, and configuration functionalities.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

JJWT (JSON Web Token for Java)

The JJWT library is indispensable for handling JWTs in Java. It streamlines the creation, parsing, and validation of JWTs. The library's modular design necessitates the inclusion of specific modules for complete functionality.

API Module

The API module outlines the JWT standards and interfaces, acting as the core that dictates our interaction with JWTs.

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
```

Implementation Module

The Implementation module houses the mechanisms that process JWTs in accordance with the API module's definitions.

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
</dependency>
```

Jackson Integration

To facilitate smooth conversion between JWTs and JSON, the Jackson Integration module integrates Jackson into the JJWT library.

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
</dependency>
```

Core Entities: User and Role

In the realm of Spring Security, the `User` and `Role` classes are pivotal. These entities are not merely data structures; they form the backbone of our security model, ensuring that each individual within our system is properly identified and granted the appropriate level of access.

User Class

The `User` class is tailored to meet the requirements of Spring Security. By implementing `UserDetails`, we provide Spring Security with crucial information such as whether the account is expired or locked, and what authorities (or roles) the user possesses. This integration allows for a seamless integration with Spring Security's authentication and authorization mechanisms.

Role Enum Class

The `Role` enum defines the different roles available within our system. By implementing `GrantedAuthority`, we enable Spring Security to recognize these roles as authorities that can be assigned to users. This is essential for enforcing role-based access control throughout our application.

JwtAuthFilter: The Gatekeeper

The `JwtAuthFilter` acts as a vigilant guardian at the gates of our application, scrutinizing every incoming request to ensure only authenticated users can access protected resources. This component extends `OncePerRequestFilter`, ensuring that our security checks are applied once per request.

Key Responsibilities of JwtAuthFilter

1.Token Extraction: Retrieves the bearer token from the Authorization header of each request.
2. Identity Verification: Parses the token to extract the username and fetches the corresponding user details.
3. Authentication: If the token is valid and the user is not already authenticated, the filter crafts a `UsernamePasswordAuthenticationToken` and registers it with Spring Security's `SecurityContextHolder`.
4. Access Control: Ensures that no request bypasses our strict token-based authentication mechanism.

JwtService: The Master of Ceremonies

The `JwtService` orchestrates the validation and creation of JWT tokens, ensuring that only legitimate and validated users can access the protected areas of our application. It uses the secret key and expiration values to determine the token's durability and safety.

Key Functions of JwtService

-Token Generation: Crafts the JWT token, embedding critical user information.
- Token Validation: Verifies the token's integrity and relevance against the user's details, rejecting expired tokens.

SecurityConfig: The Architect’s Blueprint

The `SecurityConfig` class acts as the master blueprint for our Spring application's security framework. It integrates `JwtAuthFilter` into the security chain, defines whitelisted URLs, enforces stateless session management, and sets up a custom `AuthenticationProvider`.

Key Components of SecurityConfig

-JwtAuthFilter Integration: Ensures every request is checked for a valid JWT, except for whitelisted URLs.
- Whitelist URLs: Defines endpoints accessible without authentication, typically limited to essential authentication-related paths.
- Security Filter Chain: Defines rules for request authentication and stateless session management.
- Custom Authentication Provider: Integrates Spring Security with our user service and password encoder.
- CSRF Protection: Disables CSRF protection in a stateless API, as it's not required and could complicate API interactions.

  @Ojiambo Felix
