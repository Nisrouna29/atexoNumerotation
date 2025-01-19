package com.test.technique.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuration for generating the first name part of the numerotation.
 * <p>
 * This class represents the configuration used to generate the first name portion of the numerotation.
 * It includes the desired length of the first name segment, as well as optional prefix and suffix values.
 * </p>
 */
@NoArgsConstructor
@Getter
@Setter
public class FirstNameConfig extends Config {

    /**
     * The length of the first name portion in the generated numerotation.
     * <p>
     * This value determines how many characters of the user's first name will be used in the generated number.
     * </p>
     */
    private int length;

    /**
     * Constructs a {@link FirstNameConfig} object with the specified values.
     *
     * @param criterionType The criterion type (must correspond to {@link CriterionType#FIRSTNAME}).
     * @param orderIndex The order index for this configuration, used to determine the position in the generated number.
     * @param length The length of the first name portion to be included in the generated number.
     * @param prefix The optional prefix to prepend to the first name portion.
     * @param suffix The optional suffix to append to the first name portion.
     */
    public FirstNameConfig(String criterionType, int orderIndex, int length, String prefix, String suffix) {
        super(criterionType, orderIndex, prefix, suffix);
        this.length = length;
    }
}
