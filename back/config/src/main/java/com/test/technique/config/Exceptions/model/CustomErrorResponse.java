package com.test.technique.config.Exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * A custom error response class that represents the details of an error encountered by the application.
 *
 * This class is used to structure the error response sent back to the client in case of exceptions or errors.
 * It includes a timestamp when the error occurred, a message describing the error, and an HTTP status code
 * representing the type of error.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomErrorResponse {

    /**
     * The timestamp when the error occurred.
     * This field holds the exact date and time the error was generated, which is useful for logging
     * and debugging purposes.
     *
     * @see Date
     */
    private Date timestamp;

    /**
     * The message describing the error.
     * This message provides details about the nature of the error, which will be sent to the client.
     * It can be customized based on the exception or error encountered in the application.
     */
    private String message;

    /**
     * The HTTP status code associated with the error.
     * This integer represents the HTTP status code returned by the server, indicating the type of error
     * (e.g., 400 for Bad Request, 404 for Not Found, 500 for Internal Server Error).
     *
     */
    private int status;
}
