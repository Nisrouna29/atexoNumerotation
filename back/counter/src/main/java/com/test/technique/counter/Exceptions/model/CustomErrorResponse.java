package com.test.technique.counter.Exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Represents a custom error response model used for API error handling.
 *
 * This class is used to structure error responses returned by the application. It includes:
 * 1. A timestamp to indicate when the error occurred.
 * 2. A message describing the error or the reason for failure.
 * 3. An HTTP status code to indicate the error's status.
 *
 * This model is typically used in exception handling to return a standardized error response to the client.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomErrorResponse {

    /**
     * The timestamp when the error occurred.
     * This is used to provide context about when the error happened.
     */
    private Date timestamp;

    /**
     * A descriptive message about the error.
     * This field provides information about the specific error that occurred.
     */
    private String message;

    /**
     * The HTTP status code related to the error.
     * This status code indicates the type of error that occurred (e.g., 400 for bad request, 404 for not found, etc.).
     */
    private int status;
}
