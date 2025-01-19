package com.test.technique.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NameFormatter implements ValueFormatter<String> {
    int length;

    @Override
    public String format(String value) {

        return padOrTruncate(value, length);
    }

    private String padOrTruncate(String input, int length) {
        var value = input.trim();
        if (length == 0) {
            return "";
        }
        if (value.length() >= length) {
            return value.substring(0, length);
        } else {
            return String.format("%-" + length + "s", value).replace(' ', '_');
        }
    }
}
