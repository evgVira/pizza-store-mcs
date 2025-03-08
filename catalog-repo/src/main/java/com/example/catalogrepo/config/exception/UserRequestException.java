package com.example.catalogrepo.config.exception;

public class UserRequestException extends RuntimeException {

    public UserRequestException(String message) {
        super(message);
    }
}
