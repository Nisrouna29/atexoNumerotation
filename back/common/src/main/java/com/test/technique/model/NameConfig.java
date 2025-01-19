package com.test.technique.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuration for generating the name part of the numerotation.
 * <p>
 * This class represents the configuration used to generate the last name portion of the numerotation.
 * It includes the desired length of the name segment, as well as optional prefix and suffix values.
 * </p>
 */
@NoArgsConstructor
@Getter
@Setter
public class NameConfig extends Config {

    /**
     * The length of the name portion in the generated numerotation.
     * <p>
     * This value determines how many characters of the user's last name will be used in the generated number.
     * </p>
     */
    private int length;

    /**
     * Constructs a {@link NameConfig} object with the specified values.
     *
     * @param criterionType The criterion type (must correspond to {@link CriterionType#NAME}).
     * @param orderIndex The order index for this configuration, used to determine the position in the generated number.
     * @param length The length of the last name portion to be included in the generated number.
     * @param prefix The optional prefix to prepend to the name portion.
     * @param suffix The optional suffix to append to the name portion.
     */
    public NameConfig(String criterionType, int orderIndex, int length, String prefix, String suffix) {
        super(criterionType, orderIndex, prefix, suffix);
        this.length = length;
    }
}
