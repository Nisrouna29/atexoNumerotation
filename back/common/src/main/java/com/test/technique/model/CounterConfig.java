package com.test.technique.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuration class for the "COUNTER" criterion type in numerotation.
 * <p>
 * This class extends {@link Config} and is used to represent the configuration for generating
 * the counter part of a numerotation. It includes a `length` field that determines the number of
 * digits for the counter, as well as the `prefix` and `suffix` for formatting.
 * </p>
 */
@NoArgsConstructor
@Getter
@Setter
public class CounterConfig extends Config {

    /**
     * The length of the counter value.
     * <p>
     * This value defines the number of digits that should be used for the counter. For example, if
     * the length is 5, the counter will be zero-padded to ensure it has 5 digits (e.g., 00001, 00002, etc.).
     * </p>
     */
    private int length;

    /**
     * Constructor to initialize the {@link CounterConfig}.
     *
     * @param criterionType The type of the criterion (should be "COUNTER" for this class).
     * @param orderIndex The order in which this criterion should appear in the final generated number.
     * @param length The length of the counter value (number of digits).
     * @param prefix The prefix to be added before the counter value.
     * @param suffix The suffix to be added after the counter value.
     */
    public CounterConfig(String criterionType, int orderIndex, int length, String prefix, String suffix) {
        super(criterionType, orderIndex, prefix, suffix);
        this.length = length;
    }
}
