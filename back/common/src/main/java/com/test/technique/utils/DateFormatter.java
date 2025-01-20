package com.test.technique.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A formatter for formatting {@link LocalDate} values.
 * <p>
 * This class implements the {@link ValueFormatter} interface and is responsible for formatting {@link LocalDate}
 * values into a string representation based on a provided date format.
 * </p>
 */
public class DateFormatter implements ValueFormatter<LocalDate> {

    /**
     * The date format pattern used to format the {@link LocalDate} value.
     * <p>
     * This pattern is passed to {@link DateTimeFormatter} to format the {@link LocalDate} value.
     * </p>
     */
    private final String dateFormat;

    /**
     * Constructs a {@link DateFormatter} with the specified date format.
     *
     * @param dateFormat The format pattern to be used for formatting the date (e.g., "yyyy-MM-dd").
     */
    public DateFormatter(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Formats the given {@link LocalDate} value based on the specified format pattern.
     *
     * @param value The {@link LocalDate} value to be formatted.
     * @return The formatted date as a string, or an empty string if the input value is null.
     */
    @Override
    public String format(LocalDate value) {
        return value.format(DateTimeFormatter.ofPattern(dateFormat));
    }
}
