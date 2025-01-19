package com.test.technique.model;

/**
 * Interface for configurations that include a {@code length} field.
 * <p>
 * This interface is used by configuration classes that have a length property, which represents
 * the number of digits or characters used for the respective configuration. Implementing this interface
 * ensures that the configuration class has a `getLength()` method for retrieving the length and a
 * `setLength(int length)` method for modifying the length.
 * </p>
 */
public interface LengthConfig {

    /**
     * Gets the length of the configuration.
     * <p>
     * This method returns the length of the configuration, which could represent the number of digits,
     * characters, or other applicable units depending on the specific configuration type.
     * </p>
     *
     * @return the length of the configuration
     */
    int getLength();

    /**
     * Sets the length of the configuration.
     * <p>
     * This method allows the length of the configuration to be set. The length might represent the number
     * of digits, characters, or another metric depending on the specific configuration type.
     * </p>
     *
     * @param length the new length to set for the configuration
     */
    void setLength(int length);
}

