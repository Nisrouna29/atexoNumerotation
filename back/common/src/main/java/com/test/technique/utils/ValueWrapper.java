package com.test.technique.utils;

/**
 * A utility class for wrapping a string value with optional prefix and suffix.
 * <p>
 * This class provides functionality to wrap a given string value with a prefix and/or suffix.
 * The wrapping is done by appending the prefix, the value itself, and the suffix in the specified order.
 * If any of these parts are `null` or empty, they are omitted from the result.
 * </p>
 * The resulting string is returned in uppercase.
 */
public class ValueWrapper {

    /**
     * Wraps the given string value with the specified prefix and suffix.
     * <p>
     * The method checks if the prefix, value, and suffix are not `null` or empty. If they are not,
     * they are concatenated in the order: prefix + value + suffix.
     * </p>
     * The final result is converted to uppercase.
     *
     * @param value  The string value to be wrapped.
     * @param prefix The optional prefix to be added before the value.
     * @param suffix The optional suffix to be added after the value.
     * @return A string that is the result of concatenating prefix, value, and suffix, all in uppercase.
     */
    public String wrap(String value, String prefix, String suffix) {
        StringBuilder result = new StringBuilder();

        appendIfNotNull(result, prefix);
        appendIfNotNull(result, value);
        appendIfNotNull(result, suffix);

        return result.toString().toUpperCase();
    }

    /**
     * Appends the given part to the StringBuilder if it is not null or empty.
     *
     * @param builder The StringBuilder to append to.
     * @param part    The string part to append.
     */
    private void appendIfNotNull(StringBuilder builder, String part) {
        if (part != null && !part.trim().isEmpty()) {
            builder.append(part.replaceAll("\\s+", "") );
        }
    }
}
