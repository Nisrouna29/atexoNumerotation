package com.test.technique.utils;

import lombok.AllArgsConstructor;

/**
 * A formatter for formatting {@link String} values, specifically for names.
 * <p>
 * This class implements the {@link ValueFormatter} interface and is responsible for formatting string values by either
 * truncating or padding them to a specified length.
 * </p>
 */
@AllArgsConstructor
public class NameFormatter implements ValueFormatter<String> {

    /**
     * The length to which the name should be truncated or padded.
     * <p>
     * If the input string is longer than this length, it will be truncated.
     * If it's shorter, it will be padded with underscores.
     * </p>
     */
    int length;

    /**
     * Formats the input string to the specified length by either truncating or padding it.
     *
     * @param value The string value (e.g., a name) to be formatted.
     * @return The formatted string, either truncated or padded, to match the specified length.
     */
    @Override
    public String format(String value) {
        return padOrTruncate(value, length);
    }

    /**
     * Pads or truncates the input string to the specified length.
     * <p>
     * If the string is longer than the specified length, it will be truncated to fit the length.
     * If it's shorter, it will be padded with underscores ('_') to match the desired length.
     * </p>
     *
     * @param input The input string to be formatted.
     * @param length The length to which the string should be truncated or padded.
     * @return The formatted string, either truncated or padded.
     */
    private String padOrTruncate(String input, int length) {

        var value = input.replaceAll("\\s+", "");

        if (value.length() >= length) {
            return value.substring(0, length);
        } else {
            return String.format("%-" + length + "s", value).replace(' ', '_');
        }
    }
}
