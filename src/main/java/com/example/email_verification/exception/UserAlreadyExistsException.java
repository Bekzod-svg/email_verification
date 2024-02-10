package com.example.email_verification.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException (String message){
        super(message);
    }
}
