package com.test.technique.utils;

/**
 * A formatter for formatting counter values.
 * <p>
 * This class implements the {@link ValueFormatter} interface and is responsible for formatting integer counter values.
 * It ensures that the value is padded with leading zeros to match the specified length, and truncates if necessary.
 * </p>
 */
public class CounterFormatter implements ValueFormatter<Integer> {

    /**
     * The length of the formatted counter value.
     * <p>
     * This value determines the length of the resulting formatted string. The counter value will be padded with leading
     * zeros if its length is shorter than this value. If the length of the counter exceeds this value, it will be truncated.
     * </p>
     */
    private final int length;

    /**
     * Constructs a {@link CounterFormatter} with the specified length.
     *
     * @param length The length of the formatted counter value.
     */
    public CounterFormatter(int length) {
        this.length = length;
    }

    /**
     * Formats the given counter value by padding with leading zeros or truncating as necessary.
     *
     * @param value The counter value to be formatted.
     * @return The formatted counter value as a string.
     */
    @Override
    public String format(Integer value) {
        if (length == 0) {
            return "";
        }
        if(value == null) value = 0;
        // Format the value with leading zeros to match the specified length
        String formatted = String.format("%0" + length + "d", value);

        // Truncate if the formatted value exceeds the specified length
        if (formatted.length() > length) {
            formatted = formatted.substring(0, length);
        }

        return formatted;
    }
}
