package com.test.technique.config.Exceptions;

import com.test.technique.config.Exceptions.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Global Exception Handler for handling application-wide exceptions.
 *
 * This class is annotated with {@link RestControllerAdvice}, allowing it to handle exceptions globally
 * across all controllers in the application. It provides custom error responses for various types of exceptions.
 * The goal is to return consistent error responses to the client with appropriate status codes and messages.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles general exceptions ( {@link Exception} ).
     *
     * This method is invoked when an exception of type {@link Exception} is thrown. It creates a
     * {@link CustomErrorResponse} with the exception message, the timestamp of the error, and an HTTP status
     * of {@link HttpStatus#INTERNAL_SERVER_ERROR} (500). It returns this error response to the client.
     *
     * @param ex The exception that was thrown.
     * @return A {@link ResponseEntity} containing the {@link CustomErrorResponse} and the associated
     *         {@link HttpStatus#INTERNAL_SERVER_ERROR} status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
    }

    /**
     * Handles {@link IllegalArgumentException} exceptions.
     *
     * This method is invoked when an {@link IllegalArgumentException} is thrown. It creates a
     * {@link CustomErrorResponse} with the exception message, the timestamp of the error, and an HTTP status
     * of {@link HttpStatus#BAD_REQUEST} (400). It returns this error response to the client.
     *
     * @param ex The exception that was thrown.
     * @return A {@link ResponseEntity} containing the {@link CustomErrorResponse} and the associated
     *         {@link HttpStatus#BAD_REQUEST} status.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); // 400 Bad Request
    }
}
