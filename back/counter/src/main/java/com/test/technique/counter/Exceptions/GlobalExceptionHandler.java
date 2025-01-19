package com.test.technique.counter.Exceptions;

import com.test.technique.counter.Exceptions.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Global exception handler for handling all exceptions across the application.
 *
 * This class is annotated with {@link RestControllerAdvice} to globally handle exceptions thrown
 * by any controller in the Spring application. It provides centralized error handling and returns
 * standardized error responses.
 *
 * It captures {@link Exception} and creates a {@link CustomErrorResponse} to provide useful
 * error details (timestamp, message, and status).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all general server-side exceptions (500 Internal Server Error).
     *
     * This method is invoked whenever an {@link Exception} is thrown in the application. It captures
     * the exception details, including the error message, and wraps them in a {@link CustomErrorResponse}
     * to return to the client with an appropriate HTTP status code.
     *
     * @param ex The exception that was thrown during the request processing.
     * @return A {@link ResponseEntity} containing the error response with status 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        // Create a custom error response with current timestamp and exception message
        CustomErrorResponse errorResponse = new CustomErrorResponse(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

        // Return the custom error response with HTTP status 500 (Internal Server Error)
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
