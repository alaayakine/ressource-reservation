# Resource Reservation Application

## Introduction

We want to create an application based on a micro-service architecture that allows you to manage reservations regarding resources. Each reservation concerns a single resource. The system consists of three technical micro-services to be set up are:
- The Gateway service based on Spring cloud Gateway
- The Discovery service based on Eureka Server Discovery
- The configuration service based on Spring cloud config</br>

And two other micro-services :  
- Ressource Service : which allows you to manage ressources, a resource is defined by its
id, its name, its type (MATERIEL_INF0, MATERIEL_AUDIO_VUSUEL)
- Reservation Service : that allows you to manage reservations made by people a reservation is defined by its id, its name, its context, its date, its duration. Each reservation is made by one person. A person is defined by its id, name, email and function.

## Outils
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](	https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
```
- Spring Cloud
- Eureka Discovery
- H2DataBase
- Keycloak
- Docker
```

## Project Architecture 
<table align="center">
  <tr><img src="screenshots/52.png"/></tr>
</table>
<br>

## Project Structure 
```
Ressource-Reservation-App

  |__ angular-app
        |__ src
            |__ assets
            |__ app
                |__ guard
                |__ models
                |__ pages
                    |__ personne-detail
                    |__ personne
                    |__ reservation-detail
                    |__ reservation
                    |__ ressource
                    |__ welcome
                |__ services

  |__ config-git-repo
        |__ applications properties files

  |__ config-service
        |__ src
            |__ main
                |__ java/com/exemple/configservice
                |__ resources

  |__ discovery-service
        |__ src
            |__ main
                |__ java/com/exemple/discoveryservice
                |__ resources

  |__ gateway-service
        |__ src
            |__ main
                |__ java/com/exemple/gatewayservice
                |__ resources

  |__ reservation-service
        |__ src
            |__ main
                |__ java/com/exemple/reservationservice
                    |__ dto
                    |__ entities
                    |__ enums
                    |__ mappers
                    |__ repository
                    |__ ressource
                    |__ security
                    |__ service
                    |__ web
                |__ resources

  |__ ressource-service
        |__ src
            |__ main
                |__ java/com/exemple/ressourceservice
                    |__ dto
                    |__ entities
                    |__ enums
                    |__ mappers
                    |__ repository
                    |__ security
                    |__ service
                    |__ web
                |__ resources
```
<br>

## Eureka Service 
<table align="center">
  <tr>
    <th>Run</th>
    <th>Test</th>
  </tr>
  <tr>
    <td><img src="screenshots/5.png"/></td>
    <td><img src="screenshots/6.png"/></td>
  </tr>
</table>
<br>

## Config & Gateway Service
<table align="center">
  <tr>
    <th>Config Actuator</th>
    <th>Gateway Actuator</th>
  </tr>
  <tr>
    <td><img src="screenshots/8.png"/></td>
    <td><img src="screenshots/7.png"/></
  </tr>
</table>
<br>

## Ressource Service API Doc
<table align="center">
  <tr>
    <th>Rest API</th>
    <th>Get</th>
   </tr>
  <tr>
    <td><img src="screenshots/9.png"/></td>
    <td><img src="screenshots/10.png"/></td>
  </tr>
    <th>Post</th>
    <th>Get Id</th>
  <tr>
    <td><img src="screenshots/11.png"/></td>
    <td><img src="screenshots/12.png"/></td>
  </tr>
   </tr>
    <th>Update</th>
    <th>Delete</th>
  <tr>
    <td><img src="screenshots/13.png"/></td>
    <td><img src="screenshots/14.png"/></td>
  </tr>
</table>
<br>

## Reservation Service API Doc
<table align="center">
  <tr>
    <th>Reservation Rest API</th>
    <th>Personne Rest API</th>
   </tr>
  <tr>
    <td><img src="screenshots/15.png"/></td>
    <td><img src="screenshots/16.png"/></td>
  </tr>
    <th>Get Reservation</th>
    <th>Post Personne</th>
  <tr>
    <td><img src="screenshots/17.png"/></td>
    <td><img src="screenshots/18.png"/></td>
  </tr>
  </tr>
    <th>Update Reservation</th>
    <th>Delete Personne</th>
  <tr>
    <td><img src="screenshots/19.png"/></td>
    <td><img src="screenshots/20.png"/></td>
  </tr>
</table>
<br>

## OpenFeign & Circuit Breaker
- OpenFeign is a declarative web service client that allows you to define RESTful web services as interfaces, and it automatically translates these interfaces into HTTP requests. This approach simplifies the integration of external services into your application.
- Circuit breaker is a design pattern used in distributed systems to prevent a single service failure from cascading and causing the entire system to fail. It is a way to handle faults and latency in a distributed environment. The circuit breaker pattern is commonly associated with the idea of providing fault tolerance and resilience.
```java
@FeignClient(name = "RESSOURCE-SERVICE",configuration = OpenFiegnConfiguration.class)
public interface RessourceOpenFeign {

    @GetMapping("/ressources/{id}")
    @CircuitBreaker(name = "ressourceService", fallbackMethod = "getDefaultRessource")
    Ressource getRessourceById(@PathVariable("id") int id);

    @GetMapping("/ressources")
    @CircuitBreaker(name = "ressourceService", fallbackMethod = "getAllDefaultRessources")
    List<Ressource> getAllRessources();

    default Ressource getDefaultRessource(int id, Exception exception){
        Ressource ressource=new Ressource();
        ressource.setId(id);
        ressource.setNom("Not Vailable");
        ressource.setType(null);
        return ressource;
    }
    default List<Ressource> getAllDefaultRessources(Exception exception){
        return List.of();
    }
}
```
<br>

## Angular Frontend
<table align="center">
  <tr>
    <th>Authentication</th>
    <th>Home</th>
   </tr>
  <tr>
    <td><img src="screenshots/51.png"/></td>
    <td><img src="screenshots/24.png"/></td>
  </tr>
  <tr>
    <th>Ressource Table</th>
    <th>Search</th>
  </tr>  
  <tr>
    <td><img src="screenshots/25.png"/></td>
    <td><img src="screenshots/26.png"/></td>
  </tr>
  <tr>
    <th>Add</th>
    <th>Overview</th>
  </tr>
  <tr>
    <td><img src="screenshots/27.png"/></td>
    <td><img src="screenshots/28.png"/></td>
  </tr>
  <tr>
    <th>Update</th>
    <th>Overview</th>
  </tr>
  <tr>
    <td><img src="screenshots/29.png"/></td>
    <td><img src="screenshots/30.png"/></td>
  </tr>
  <tr>
    <th>Ressource Reservation</th>
    <th>Overview</th>
  </tr>
  <tr>
    <td><img src="screenshots/32.png"/></td>
    <td><img src="screenshots/33.png"/></td>
  </tr>
</table>
<br><br>
<table align="center">
  <tr>
    <th>Reservation Table</th>
    <th>Search</th>
  </tr>  
  <tr>
    <td><img src="screenshots/34.png"/></td>
    <td><img src="screenshots/35.png"/></td>
  </tr>
  <tr>
    <th>Add</th>
    <th>Overview</th>
  </tr>
  <tr>
    <td><img src="screenshots/36.png"/></td>
    <td><img src="screenshots/37.png"/></td>
  </tr>
  <tr>
    <th>Update</th>
    <th>Overview</th>
  </tr>
  <tr>
    <td><img src="screenshots/39.png"/></td>
    <td><img src="screenshots/40.png"/></td>
  </tr>
  <tr>
    <th>Delete</th>
    <th>Reservation detail</th>
  </tr>
  <tr>
    <td><img src="screenshots/41.png"/></td>
    <td><img src="screenshots/38.png"/></td>
  </tr>
</table>
<br><br>
<table align="center">
  <tr>
    <th>Personne Table</th>
    <th>Search</th>
  </tr>  
  <tr>
    <td><img src="screenshots/42.png"/></td>
    <td><img src="screenshots/43.png"/></td>
  </tr>
  <tr>
    <th>Add</th>
    <th>Overview</th>
  </tr>
  <tr>
    <td><img src="screenshots/44.png"/></td>
    <td><img src="screenshots/45.png"/></td>
  </tr>
  <tr>
    <th>Update</th>
    <th>Overview</th>
  </tr>
  <tr>
    <td><img src="screenshots/46.png"/></td>
    <td><img src="screenshots/47.png"/></td>
  </tr>
  <tr>
    <th>Delete</th>
    <th>Personne detail</th>
  </tr>
  <tr>
    <td><img src="screenshots/48.png"/></td>
    <td><img src="screenshots/49.png"/></td>
  </tr>
</table>
<br>

## Application Security
To Secure the microservices, we need and an authentication layer and authorisation protocol, Therefore we will use OIDC, OAuth and Keycloak as a provider.

### 1. OAuth
OAuth is an authorization framework that allows a third-party application to obtain limited access to an HTTP service on behalf of a resource owner, such as a user. It is not an authentication protocol, instead, it focuses on delegated access and authorization, and it supports various grant types, such as Authorization Code, Implicit, Resource Owner Password Credentials, and Client Credentials.
<table align="center">
  <tr>
    <th>How It Work</th>
   </tr>
  <tr>
    <td><img src="screenshots/54.png"/></td>
  </tr>
</table>
<br>

### 2. OIDC
OpenID Connect is an identity layer on top of OAuth. It adds an authentication layer to OAuth, allowing clients to verify the identity of the end-user based on the authentication performed by an authorization server, as well as to obtain basic profile information about the user. OIDC Components:
- ID Token: A JWT (JSON Web Token) that contains information about the authenticated user.
- UserInfo Endpoint: An endpoint where additional user information can be retrieved.
- Discovery: A mechanism for dynamically discovering OIDC endpoints and configuration.
<table align="center">
  <tr>
    <th>How It Work</th>
   </tr>
  <tr>
    <td><img src="screenshots/55.png"/></td>
  </tr>
</table>
<br>

### 3. Keycloak
Keycloak is an open-source Identity and Access Management solution. It provides functionalities for user authentication, authorization, and management, and it supports various standards, including OAuth and OIDC. Keycloak can be configured as an OAuth Authorization Server and an OIDC Provider. Clients in Keycloak are registered with the necessary settings and can use OAuth or OIDC flows for authentication and authorization. Keycloak's Role:
- Authentication: Keycloak handles the authentication process, including supporting various identity providers.
- Authorization: Keycloak serves as an authorization server, managing access tokens and enforcing authorization policies.
- User Management: Keycloak allows administrators to manage users, roles, and permissions.
- Single Sign-On (SSO): Keycloak supports SSO, allowing users to log in once and access multiple applications without re-authenticating.
<table align="center">
  <tr>
    <th>How It Work</th>
   </tr>
  <tr>
    <td><img src="screenshots/53.png"/></td>
  </tr>
</table>
<br>

### 4. Steps
<table align="center">
  <tr>
    <th>Run Keycloak</th>
    <th>Authentication</th>
   </tr>
  <tr>
    <td><img src="screenshots/2.png"/></td>
    <td><img src="screenshots/3.png"/></td>
  </tr>
  <tr>
    <th>Create Realm</th>
    <th>Create Client</th>
   </tr>
  <tr>
    <td><img src="screenshots/4.png"/></td>
    <td><img src="screenshots/21.png"/></td>
  </tr>
  <tr>
    <th>Create Users</th>
    <th>Create Roles</th>
   </tr>
  <tr>
    <td><img src="screenshots/22.png"/></td>
    <td><img src="screenshots/23.png"/></td>
  </tr>
</table>

- Implementing Authentication: When a user attempts to log in to your application, they are redirected to Keycloak. Here, they authenticate using their credentials. Upon successful authentication, Keycloak issues an ID token (for OIDC) and an access token (JWT). The ID token contains information about the user's identity, while the access token includes user roles and permissions.</br>
Maven dependency
```java
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```
Application.properties
```java
spring.security.oauth2.resourceserver.jwt.issuer-uri=${JWT_ISSUER_URI:http://localhost:8080/realms/sdia-realm}
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=${JWT_JWK_SET_URI:http://localhost:8080/realms/sdia-realm/protocol/openid-connect/certs}
```
- Securing the Backend (APIs): The backend services validate the JWT on each request. This can be done using Keycloak's adapters or libraries that can validate JWTs. Implement access control checks based on the roles and permissions contained in the JWT. You can use annotations like @PreAuthorize in your controllers for role-based access control.
```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  return http
    .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
    .oauth2ResourceServer(o2->o2.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter)))
    .headers(h->h.frameOptions(fo->fo.disable()))
    .csrf(csrf->csrf.ignoringRequestMatchers("/h2-console/**"))
    .build();
}
```
- Securing the Frontend Application: Store the tokens securely in the client application . Include the JWT in the Authorization header when making requests to protected resources.
- Config OpenFeign Rest Interface, so it will be able to access to the Ressource service
```java
@Component
public class OpenFiegnConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext context = SecurityContextHolder.getContext();
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) context.getAuthentication();
        String tokenValue = authentication.getToken().getTokenValue();
        requestTemplate.header("Authorization","Bearer "+tokenValue);
    }
}
```
</br>

## Docker Deployment
```java
services:
  postgres-service:
    image: postgres
    container_name: postgres-service
    volumes:
      - postgres_data:/var/lib/postgresql/data
    environment:
      POSTGRES_DB: keycloak
      POSTGRES_USER: keycloak
      POSTGRES_PASSWORD: ****
    ports:
      - '5432:5432'
    expose:
      - '5432'
    healthcheck:
      test: "exit 0"
  pgadmin4:
    image: dpage/pgadmin4
    container_name: pgadmin4
    restart: always
    ports:
      - "8083:80"
    environment:
      PGADMIN_DEFAULT_EMAIL: med@gmail.com
      PGADMIN_DEFAULT_PASSWORD: ****
    volumes:
      - pgadmin_data:/var/lib/pgadmin
  keycloak:
    image: quay.io/keycloak/keycloak:latest
    environment:
      KC_DB: postgres
      KC_DB_URL: jdbc:postgresql://postgres-service:5432/keycloak
      KC_DB_USERNAME: keycloak
      KC_DB_PASSWORD: ****
      KEYCLOAK_ADMIN: admin
      KC_HTTP_ENABLED: "true"
      KC_HOSTNAME_STRICT_HTTPS: "false"
      KEYCLOAK_ADMIN_PASSWORD: ****
    command:
      - start-dev
    restart: always
    ports:
      - '8080:8080'
    expose:
      - '8080'
    depends_on:
      - postgres-service
  discovery-service:
    build: ./discovery-service
    container_name: discovery-service
    ports:
      - '8761:8761'
    expose:
      - '8761'
    healthcheck:
      test: [ "CMD", "curl", "-f", "http://localhost:8761/actuator/health" ]
      interval: 10s
      retries: 4
  config-service:
    build: ./config-service
    container_name: config-service
    ports:
      - '9999:9999'
    expose:
      - '9999'
    environment:
      - DISCOVERY_SERVICE_URL=http://discovery-service:8761/eureka
    healthcheck:
      test: [ "CMD", "curl", "-f", "http://localhost:9999/actuator/health" ]
      interval: 10s
      retries: 4
    depends_on:
      discovery-service:
        condition: service_healthy
  gateway-service:
    build: ./gateway-service
    container_name: gateway-service
    ports:
      - '8888:8888'
    expose:
      - '8888'
    environment:
      - DISCOVERY_SERVICE_URL=http://discovery-service:8761/eureka
      - CONFIG_SERVICE_URL=http://config-service:9999
    depends_on:
      config-service:
        condition: service_healthy
  ressource-service:
    build: ./ressource-service
    container_name: ressource-service
    ports:
      - '8081:8081'
    expose:
      - '8081'
    environment:
      - DISCOVERY_SERVICE_URL=http://discovery-service:8761/eureka
      - CONFIG_SERVICE_URL=http://config-service:9999
      - JWT_ISSUER_URI:http://keycloak:8080/realms/sdia-realm
      - JWT_JWK_SET_URI:http://keycloak:8080/realms/sdia-realm/protocol/openid-connect/certs
    healthcheck:
      test: [ "CMD", "curl", "-f", "http://localhost:8081/actuator/health" ]
      interval: 10s
      retries: 4
    depends_on:
      config-service:
        condition: service_healthy
  reservation-service:
    build: ./reservation-service
    container_name: reservation-service
    ports:
      - '8082:8082'
    expose:
      - '8082'
    environment:
      - DISCOVERY_SERVICE_URL=http://discovery-service:8761/eureka
      - CONFIG_SERVICE_URL=http://config-service:9999
      - JWT_ISSUER_URI:http://keycloak:8080/realms/sdia-realm
      - JWT_JWK_SET_URI:http://keycloak:8080/realms/sdia-realm/protocol/openid-connect/certs
    depends_on:
      ressource-service:
        condition: service_healthy
  angular-app:
    build: ./angular-app
    container_name: angular-app
    ports:
      - '8085:80'
    expose:
      - '8085'
    restart: always
    depends_on:
      - keycloak
      - ressource-service
      - reservation-service
volumes:
  postgres_data:
  pgadmin_data:
```

## License
[MIT LICENSE](LICENSE)

