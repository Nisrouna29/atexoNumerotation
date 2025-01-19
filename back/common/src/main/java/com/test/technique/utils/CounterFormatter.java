package com.test.technique.utils;

public class CounterFormatter implements ValueFormatter<Integer> {
    private final int length;

    public CounterFormatter(int length) {
        this.length = length;
    }

    @Override
    public String format(Integer value) {
        if (length == 0) {
            return "";
        }
        String formatted = String.format("%0" + length + "d", value);
        if (formatted.length() > length) {
            formatted = formatted.substring(0, length); // Truncate if needed
        }
        return formatted;
    }
}

