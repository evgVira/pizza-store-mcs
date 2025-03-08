package com.example.orderrepo.config.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFound(ResourceNotFoundException exception) {
        logError(exception, HttpStatus.NOT_FOUND);
        return buildErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRequestException.class)
    public ResponseEntity<ErrorDto> handleUserRequestException(UserRequestException exception) {
        logError(exception, HttpStatus.BAD_REQUEST);
        return buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private void logError(Exception exception, HttpStatus status) {
        LOGGER.error("Ошибка: {} - {}: {}", status.value(), status.getReasonPhrase(), exception.getMessage(), exception);
    }

    private ResponseEntity<ErrorDto> buildErrorResponse(String message, HttpStatus status) {
        ErrorDto errorDto = new ErrorDto(status.value(), status.getReasonPhrase(), message);
        return new ResponseEntity<>(errorDto, status);
    }
}
