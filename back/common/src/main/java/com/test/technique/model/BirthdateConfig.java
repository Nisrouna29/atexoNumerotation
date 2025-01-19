package com.test.technique.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Configuration class for managing birthdate-related formatting in the numerotation process.
 * <p>
 * This class extends {@link Config} and adds the specific configuration for handling birthdates,
 * including the format in which the birthdate should be displayed.
 * </p>
 */
@NoArgsConstructor
@Getter
@Setter
public class BirthdateConfig extends Config {

    /**
     * The date format pattern to be used for formatting the birthdate.
     * <p>
     * This field specifies the desired format for the birthdate (e.g., "yyyy-MM-dd").
     * </p>
     */
    private String dateFormat;

    /**
     * Constructor for creating a {@link BirthdateConfig} instance.
     *
     * @param criterionType The type of criterion (e.g., "BIRTHDATE").
     * @param orderIndex The order index to determine the placement of this criterion in the sequence.
     * @param prefix The prefix to prepend to the formatted birthdate.
     * @param suffix The suffix to append to the formatted birthdate.
     * @param dateFormat The format pattern to be used for the birthdate (e.g., "yyyy-MM-dd").
     */
    public BirthdateConfig(String criterionType, int orderIndex, String prefix, String suffix, String dateFormat) {
        super(criterionType, orderIndex, prefix, suffix);
        this.dateFormat = dateFormat;
    }

}
