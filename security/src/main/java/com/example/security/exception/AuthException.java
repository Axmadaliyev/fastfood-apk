package com.example.security.exception;

@SuppressWarnings("serial")
public class AuthException extends RuntimeException {

    public AuthException() {
        super();
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(String message) {
        super(message);
    }

}
