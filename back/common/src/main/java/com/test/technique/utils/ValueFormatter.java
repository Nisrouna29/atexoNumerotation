package com.test.technique.utils;

import com.test.technique.model.Config;

/**
 * A generic interface for formatting values of type {@link T}.
 * <p>
 * This interface defines a contract for any class that formats values of a given type {@link T}. The implementing classes
 * should provide their own logic to format the value as a {@link String}.
 * </p>
 *
 * @param <T> the type of the value to be formatted (e.g., String, Integer, LocalDate).
 */
public interface ValueFormatter<T> {

    /**
     * Formats a given value of type {@link T} into a {@link String}.
     *
     * @param value The value to be formatted.
     * @return The formatted value as a {@link String}.
     */
    String format(T value);
}
