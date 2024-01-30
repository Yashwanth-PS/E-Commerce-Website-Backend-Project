package com.EcommerceUserService.exception;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message) {
        super(message);
    }

    public InvalidCredentialException() {
    }

    public InvalidCredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}
