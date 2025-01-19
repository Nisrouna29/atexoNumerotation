package com.test.technique.numerotation.Exceptions;

import com.test.technique.numerotation.Exceptions.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Global exception handler for the application.
 * <p>
 * This class provides centralized exception handling for all controllers in the application.
 * It catches exceptions thrown by the application and formats them into a consistent error
 * response that is returned to the client.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles general exceptions (Exception.class).
     * <p>
     * This method catches all uncaught exceptions (those that are not explicitly handled
     * elsewhere) and creates a standard error response with a timestamp, the error message,
     * and an internal server error (500) status.
     * </p>
     *
     * @param ex The exception that was thrown
     * @return ResponseEntity with the error response and HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                new Date(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles illegal argument exceptions (IllegalArgumentException.class).
     * <p>
     * This method catches cases where invalid input is provided by the client (e.g., invalid
     * data or wrong parameters in the request) and returns a bad request (400) error response.
     * </p>
     *
     * @param ex The exception that was thrown
     * @return ResponseEntity with the error response and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                new Date(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
