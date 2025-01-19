package com.test.technique.utils;

// ValueWrapper.java

public class ValueWrapper {
    public String wrap(String value, String prefix, String suffix) {
        StringBuilder result = new StringBuilder();

        appendIfNotNull(result, prefix);
        appendIfNotNull(result, value);
        appendIfNotNull(result, suffix);

        return result.toString().toUpperCase();
    }
    private void appendIfNotNull(StringBuilder builder, String part) {
        if (part != null) {
            builder.append(part);
        }
    }
}

