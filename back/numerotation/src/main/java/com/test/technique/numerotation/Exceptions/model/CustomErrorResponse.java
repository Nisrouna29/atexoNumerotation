package com.test.technique.numerotation.Exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Custom error response model that is returned in case of exceptions.
 *
 * This class is used to structure the error details sent back to the client whenever an exception
 * occurs in the system. The response includes a timestamp, a message describing the error,
 * and the HTTP status code associated with the error.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomErrorResponse {

    /**
     * The timestamp indicating when the error occurred.
     *
     * This field holds the exact time when the error was generated,
     * typically represented in `Date` format. It is used for logging
     * purposes and to inform the client of when the error happened.
     */
    private Date timestamp;

    /**
     * A message describing the error that occurred.
     *
     * This field provides a human-readable description of the error that will
     * be sent to the client. It helps the client understand the nature of the error.
     */
    private String message;

    /**
     * The HTTP status code associated with the error.
     *
     * This field holds the HTTP status code (e.g., 404, 500, 400)
     * that corresponds to the error. It is used to provide more context
     * for the client about the type of error that occurred.
     */
    private int status;

}
