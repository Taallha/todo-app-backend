# todo-app-backend


## Summary
This project contains the backend service for a todo application. It provides basic CRUD (Create, Read, Update, Delete) operations using RESTful APIs.

## Requirements

- Java 17
- Spring Boot
- Spring Security
- Spring Web
- Spring data Jpa
- Maven
- Postman

## Security 
  ### JWT Based Authorization and Security
  This project provides authorization and security using JSON Web Token (JWT)

  #### Getting Login and JWT
  
1. **POST /authenticate Endpoint:** The user sends a POST request to this endpoint with his username and password. 
  - Sample Request:
      ```bash
     curl -X POST http://localhost:8080/login \
          -H "Content-Type: application/json" \
          -d '{"username": "kullaniciAdi", "password": "sifre"}'
     ```
- Sample Response:
     ```json
     {
       "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
     }
     ```
![Ekran görüntüsü 2023-12-19 110852](https://github.com/Taallha/todo-app-backend/assets/115630680/54e7d51d-f765-4d5d-bf2c-f234b07bcb0e)

- Code
  ```java
  @PostMapping("/authenticate")
  public ResponseEntity<JwtTokenResponse> generateToken(
            @RequestBody JwtTokenRequest jwtTokenRequest) {
        
        var authenticationToken = 
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(), 
                        jwtTokenRequest.password());
        
        var authentication = 
                authenticationManager.authenticate(authenticationToken);
        
        var token = tokenService.generateToken(authentication);
        
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }
  ```
  #### Encoder & Decoder Configuration
  In this project, RSA Key Pair is used to sign and verify JWT (Json Web Token) tokens. Below is an example README outlining the JWT Encoder and Decoder configurations.

- Generating RSA Key Pair

  RSA Key Pair is used to sign and verify JWT tokens. This key pair is used securely for the creation and verification of tokens.
    
    ```java
    @Bean
    public KeyPair keyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Unable to generate an RSA Key Pair", e);
        }
    }
    ```
- JWKSource Bean

  JWKSource is used to provide an RSA Key Set. This provides a source with public and private keys for JwtEncoder and JwtDecoder.
```java
@Bean
public JWKSource<SecurityContext> jwkSource() {
    JWKSet jwkSet = new JWKSet(rsaKey());
    return (((jwkSelector, securityContext) 
                    -> jwkSelector.select(jwkSet)));
}
```
- JwtEncoder Bean

    JwtEncoder signs JWT tokens using JWKSource.
```java
@Bean
JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
    return new NimbusJwtEncoder(jwkSource);
}
```
- JwtDecoder Bean

  JwtDecoder verifies JWT tokens using JWKSource and RSA public key.
```java
@Bean
JwtDecoder jwtDecoder() throws JOSEException {
    return NimbusJwtDecoder
            .withPublicKey(rsaKey().toRSAPublicKey())
            .build();
}
```
#### RSA Key Pair Configuration

RSA Key Pair is used for JWT (Json Web Token) signing and verification. This configuration provides a secure method for generating an RSA Key Pair.

- RSA Key Pair Bean Configuration

  The following beans provide the RSA Key Pair used for JWT token creation and verification.
```
@Bean
public KeyPair keyPair() {
    try {
        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    } catch (Exception e) {
        throw new IllegalStateException(
                "Unable to generate an RSA Key Pair", e);
    }
}
```

- RSAKey Bean

    RSAKey bean is created with a random key ID and the generated RSA Key Pair.

```java
@Bean
public RSAKey rsaKey() {
    
    KeyPair keyPair = keyPair();
    
    return new RSAKey
            .Builder((RSAPublicKey) keyPair.getPublic())
            .privateKey((RSAPrivateKey) keyPair.getPrivate())
            .keyID(UUID.randomUUID().toString())
            .build();
}
```

## API Description 

This project provides RESTful APIs for a Todo application backend. Below are the available endpoints:

### Retrieve Todos

#### Request
Retrieve all todos for a specific user.
```http
GET /users/{username}/todos
```
#### Response
Returns a list of todos for the specified user.
![Ekran görüntüsü 2023-12-19 214108](https://github.com/Taallha/todo-app-backend/assets/115630680/2cf44666-4bc7-49c2-8486-9dacf2a7ebc0)


### Retrieve Todo
#### Request
Retrieve a specific todo by ID for the specified user.
```http
GET /users/{username}/todos/{id}
````
#### Response
Returns the details of the specified todo.
![Ekran görüntüsü 2023-12-19 214522](https://github.com/Taallha/todo-app-backend/assets/115630680/7edba96e-a96c-4dd2-922e-07b350f42d04)


### Delete Todo
#### Request
Delete a specific todo by ID for the specified user.
```http
DELETE /users/{username}/todos/{id}
```
#### Response
Returns a success response with no content.
![Ekran görüntüsü 2023-12-19 214750](https://github.com/Taallha/todo-app-backend/assets/115630680/7185c97f-7df8-42f4-8c32-2a7a11d7e1b6)


### Update Todo
#### Request
Update a specific todo by ID for the specified user.
```http
PUT /users/{username}/todos/{id}
```
#### Response
Returns the updated todo.
![Ekran görüntüsü 2023-12-19 215942](https://github.com/Taallha/todo-app-backend/assets/115630680/12d320e1-dd90-4e07-96f1-0a0e74973a9c)

### Create Todo
#### Request
Create a new todo for the specified user.
```http
POST /users/{username}/todos
```

#### Response
Returns the created todo.
![Ekran görüntüsü 2023-12-19 220333](https://github.com/Taallha/todo-app-backend/assets/115630680/38168132-653e-422b-9a45-68e4a3acb9ec)


These are the available APIs for managing todos in the Todo App Backend.






