package com.test.technique.model;

/**
 * Enum representing the different types of criteria that can be used in a numerotation.
 * <p>
 * Each criterion type corresponds to a specific part of the generated number:
 * <ul>
 *     <li>{@link #FIRSTNAME} - Represents the user's first name.</li>
 *     <li>{@link #NAME} - Represents the user's last name.</li>
 *     <li>{@link #BIRTHDATE} - Represents the user's birthdate.</li>
 *     <li>{@link #COUNTER} - Represents a unique counter value that increments with each request.</li>
 * </ul>
 * </p>
 */
public enum CriterionType {

    /**
     * Represents the user's first name as part of the generated number.
     */
    FIRSTNAME,

    /**
     * Represents the user's last name as part of the generated number.
     */
    NAME,

    /**
     * Represents the user's birthdate in a specified format as part of the generated number.
     */
    BIRTHDATE,

    /**
     * Represents an incrementing counter as part of the generated number.
     * The counter value increments each time a request is processed.
     */
    COUNTER;

    /**
     * Validates if the given string matches any of the {@link CriterionType} enum values.
     * <p>
     * This method is used to check if a given string corresponds to a valid criterion type.
     * </p>
     *
     * @param value The string value to check.
     * @return {@code true} if the value matches any of the {@link CriterionType} names,
     *         {@code false} otherwise.
     */
    public static boolean isValid(String value) {
        for (CriterionType type : values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
