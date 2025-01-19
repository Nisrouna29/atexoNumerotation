package com.test.technique.numerotation.service;

import com.test.technique.model.*;
import com.test.technique.numerotation.model.UserInfo;
import com.test.technique.utils.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * Service class responsible for generating a unique numerotation based on user information
 * and configuration settings.
 * <p>
 * This service validates the user information, sorts the configuration criteria by order,
 * and applies the appropriate formatting for each criterion type (e.g., firstname, lastname, birthdate, counter).
 * The generated number is a combination of these formatted values.
 * </p>
 */
@Service
public class NumerotationService {

    /**
     * Generates a unique numerotation string based on user information, configuration settings, and a counter.
     * <p>
     * The method iterates through the provided configuration list, formats the user data based on the
     * criterion types, and appends the formatted values to a string builder. The configurations are sorted
     * according to their order index before processing. The counter is used to append an incremental value.
     * </p>
     *
     * @param configs   List of configuration objects defining the criterion types and their associated settings.
     * @param counter   The current value of the counter to be included in the numerotation.
     * @param userInfo  The user information (first name, last name, and birthdate) used for generating the numerotation.
     * @return A string representing the generated numerotation.
     * @throws IllegalArgumentException If any of the user information fields are invalid.
     */
    public String generate(List<Config> configs, int counter, UserInfo userInfo) {
        validateUserInfo(userInfo);  // Validate user info before processing

        // Initialize StringBuilder to construct the numerotation
        StringBuilder generatedNumber = new StringBuilder();

        // Sort configurations by order index to ensure correct processing order
        configs.sort(Comparator.comparingInt(Config::getOrderIndex));

        // Wrapper for adding prefixes and suffixes around values
        ValueWrapper valueWrapper = new ValueWrapper();

        // Iterate over the sorted configurations and generate the corresponding parts of the numerotation
        for (Config config : configs) {
            CriterionType criterionType = CriterionType.valueOf(config.getCriterionType());

            switch (criterionType) {
                case FIRSTNAME:
                    FirstNameConfig firstNameConfig = (FirstNameConfig) config;
                    ValueFormatter<String> firsNameFormatter = new NameFormatter(firstNameConfig.getLength());
                    generatedNumber.append(valueWrapper.wrap(firsNameFormatter.format(userInfo.getFirstname()), firstNameConfig.getPrefix(), firstNameConfig.getSuffix()));
                    break;
                case NAME:
                    NameConfig nameConfig = (NameConfig) config;
                    ValueFormatter<String> nameFormatter = new NameFormatter(nameConfig.getLength());
                    generatedNumber.append(valueWrapper.wrap(nameFormatter.format(userInfo.getLastname()), nameConfig.getPrefix(), nameConfig.getSuffix()));
                    break;
                case BIRTHDATE:
                    BirthdateConfig birthdateConfig = (BirthdateConfig) config;
                    ValueFormatter<LocalDate> dateFormatter = new DateFormatter(birthdateConfig.getDateFormat());
                    generatedNumber.append(valueWrapper.wrap(dateFormatter.format(userInfo.getBirthdate()), birthdateConfig.getPrefix(), birthdateConfig.getSuffix()));
                    break;
                case COUNTER:
                    CounterConfig counterConfig = (CounterConfig) config;
                    ValueFormatter<Integer> counterFormatter = new CounterFormatter(counterConfig.getLength());
                    generatedNumber.append(valueWrapper.wrap(counterFormatter.format(counter), counterConfig.getPrefix(), counterConfig.getSuffix()));
                    break;
            }
        }

        // Return the final generated numerotation string
        return generatedNumber.toString();
    }

    /**
     * Validates the user information to ensure that all required fields are present and non-empty.
     * <p>
     * Throws an {@link IllegalArgumentException} if any of the user information fields are invalid.
     * </p>
     *
     * @param userInfo The user information to validate.
     * @throws IllegalArgumentException If any of the user information fields are invalid.
     */
    private void validateUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("UserInfo cannot be null");
        }
        if (userInfo.getFirstname() == null || userInfo.getFirstname().trim().isEmpty()) {
            throw new IllegalArgumentException("Firstname cannot be null or empty");
        }
        if (userInfo.getLastname() == null || userInfo.getLastname().trim().isEmpty()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty");
        }
        if (userInfo.getBirthdate() == null) {
            throw new IllegalArgumentException("Birthdate cannot be null");
        }
    }
}
