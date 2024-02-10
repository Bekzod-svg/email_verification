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
- **Spring Boot** 
- **Spring Security** 
- **Spring Data JPA** 
- **Spring Mail** 
- **MySQL** 
- **Hibernate**
- **Lombok**
- **Java Mail API**

## Endpoints
- **POST** /register: Registers a new user and triggers an email verification process.
<img width="558" alt="Screen Shot 2024-02-10 at 18 58 00" src="https://github.com/Bekzod-svg/email_verification/assets/57885639/36d8aa87-b688-4a4a-a599-5094dd3ee81a">
- Response
- <img width="712" alt="Screen Shot 2024-02-10 at 18 58 30" src="https://github.com/Bekzod-svg/email_verification/assets/57885639/097a1a59-b417-461c-869e-20ae643e9db3">

- **GET** /register/verifyEmail: Verifies the user's email address based on the provided token.
- <img width="626" alt="Screen Shot 2024-02-10 at 19 00 01" src="https://github.com/Bekzod-svg/email_verification/assets/57885639/7ad9804b-e39f-4206-a258-c90c0edb2b14">
- <img width="207" alt="Screen Shot 2024-02-10 at 19 02 27" src="https://github.com/Bekzod-svg/email_verification/assets/57885639/ba7694fa-a059-442f-9302-954688a0117a">

- **GET** /users: Retrieves a list of all registered users (requires authentication).
- <img width="1194" alt="Screen Shot 2024-02-10 at 19 03 52" src="https://github.com/Bekzod-svg/email_verification/assets/57885639/a4361cec-99c0-4310-8d00-4ea7b99242f7">
- **GET** /login:
- <img width="352" alt="Screen Shot 2024-02-10 at 19 03 14" src="https://github.com/Bekzod-svg/email_verification/assets/57885639/f0083036-9c4e-44cc-98fb-8f788149f4bd">
- <img width="118" alt="Screen Shot 2024-02-10 at 19 03 25" src="https://github.com/Bekzod-svg/email_verification/assets/57885639/802798e0-64ba-44ca-b613-9cea55ef62c2">

## Security
This application uses Spring Security to handle authentication and authorization, with BCryptPasswordEncoder for password encoding. The security configuration is defined in UserRegistrationSecurityConfig.java.

## Email Service
Emails are sent using the JavaMailSender interface, configured in the RegistrationCompleteEventListener. 
