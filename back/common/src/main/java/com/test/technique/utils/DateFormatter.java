package com.test.technique.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter implements ValueFormatter<LocalDate> {
    private final String dateFormat;

    public DateFormatter(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String format(LocalDate value) {
        if (value == null) return "";
        return value.format(DateTimeFormatter.ofPattern(dateFormat));
    }
}

