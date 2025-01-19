package com.test.technique.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base configuration class for numerotation criteria.
 * <p>
 * This class represents a generic configuration for various types of numerotation criteria.
 * It includes properties such as `criterionType`, `orderIndex`, `prefix`, and `suffix`, which can be used
 * by subclasses to handle specific types of data (e.g., first name, last name, birthdate, counter).
 * </p>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FirstNameConfig.class, name = "FirstNameConfig"),
        @JsonSubTypes.Type(value = NameConfig.class, name = "NameConfig"),
        @JsonSubTypes.Type(value = BirthdateConfig.class, name = "BirthdateConfig"),
        @JsonSubTypes.Type(value = CounterConfig.class, name = "CounterConfig")
})
public class Config {

    /**
     * The type of criterion this configuration applies to (e.g., "FIRSTNAME", "NAME", "BIRTHDATE", "COUNTER").
     */
    private String criterionType;

    /**
     * The order in which this criterion should appear in the generated output.
     * <p>
     * The `orderIndex` is used to determine the sequence of criteria when generating the final output.
     * </p>
     */
    private int orderIndex;

    /**
     * The prefix to be added before the formatted value for this criterion.
     * <p>
     * This field allows adding a string that appears at the start of the formatted value (e.g., "Mr." for name).
     * </p>
     */
    private String prefix;

    /**
     * The suffix to be added after the formatted value for this criterion.
     * <p>
     * This field allows adding a string that appears at the end of the formatted value (e.g., "Jr." for name).
     * </p>
     */
    private String suffix;
}
