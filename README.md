# Email Verification Application
## Introduction
This project is a Spring Boot application designed to register users and verify their email addresses. Upon registration, users receive an email with a verification link. Clicking this link activates their account, ensuring that only valid email addresses are used for registration.

## Features
- User registration with email verification
- Spring Security for authentication and authorization
- Custom exception handling for existing users
- Integration with an SMTP server for email sending
- Persistent storage with MySQL

## Configurations
- Java 21
- Maven
- MySQL
## Technologies Used
- **USpring Boot:** For rapid application development
- **Spring Security:** For authentication and security
- **Spring Data JPA:** For database operations
- **Spring Mail:** For email services
- **MySQL:** As the database
- **Hibernate:** For ORM
- **Lombok:** For reducing boilerplate code
- **Java Mail API:** For email operations

## Endpoints
- **POST** /register: Registers a new user and triggers an email verification process.
- **GET** /register/verifyEmail: Verifies the user's email address based on the provided token.
- **GET** /users: Retrieves a list of all registered users (requires authentication).
## Security
- This application uses Spring Security to handle authentication and authorization, with BCryptPasswordEncoder for password encoding. The security configuration is defined in UserRegistrationSecurityConfig.java.

## Email Service
Emails are sent using the JavaMailSender interface, configured in the RegistrationCompleteEventListener. 
