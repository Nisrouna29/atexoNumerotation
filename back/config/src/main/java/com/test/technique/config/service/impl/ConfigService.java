package com.test.technique.config.service.impl;

import com.test.technique.config.document.Configs;
import com.test.technique.config.repository.ConfigRepository;
import com.test.technique.config.service.IConfigService;
import com.test.technique.model.BirthdateConfig;
import com.test.technique.model.Config;
import com.test.technique.model.CriterionType;
import com.test.technique.model.LengthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Service class for managing configuration settings.
 *
 * This class provides business logic for managing configurations and validating the configuration list.
 * It interacts with the {@link ConfigRepository} to retrieve and save configuration data in the MongoDB database.
 * It ensures that the configuration list is valid before updating or creating the configurations in the database.
 *
 * {@link ConfigService} implements the {@link IConfigService} interface, providing methods to fetch all configurations,
 * update configurations, and validate the configuration list.
 */
@Service
public class ConfigService implements IConfigService {

    @Autowired
    private ConfigRepository repository;

    /**
     * Retrieves all configuration settings from the database.
     *
     * This method fetches the configuration document stored in the `configs` collection from MongoDB.
     * It returns the list of individual configurations (of type {@link Config}) if available,
     * or an empty list if no configurations exist.
     *
     * @return A list of {@link Config} objects representing the configuration settings.
     */
    @Override
    public List<Config> getAllConfigs() {
        Configs configs = repository.findById("configs").orElse(null);
        return (configs != null) ? configs.getConfigs() : Collections.emptyList();
    }

    /**
     * Validates the list of configurations to ensure they follow certain rules.
     *
     * This method checks that:
     * 1. The list contains exactly 4 configurations.
     * 2. Each configuration has a valid {@link CriterionType}. and no duplications
     * 3. The orderIndex is between 1 and 4 and does not contain duplicates.
     * 4. Config with length, length must be > 0
     * 5. Config with dateformat, dateformat must be valid
     * If any of these conditions are violated, an {@link IllegalArgumentException} is thrown with an appropriate error message.
     *
     * @param configs The list of {@link Config} objects to be validated.
     * @throws IllegalArgumentException If any validation rule is violated (e.g., invalid criterionType, invalid orderIndex, or duplicate orderIndex).
     */
    private void validateConfigs(List<Config> configs) {
        if (configs.size() == 4) {
            Set<Integer> seenOrderIndexes = new HashSet<>();
            Set<String> seenCriterionTypes = new HashSet<>();
            for (Config config : configs) {

                if (config.getCriterionType() == null || !CriterionType.isValid(config.getCriterionType())) {
                    throw new IllegalArgumentException("Invalid criterionType: " + config.getCriterionType());
                }

                // Check for duplicate criterionType
                if (!seenCriterionTypes.add(config.getCriterionType())) {
                    throw new IllegalArgumentException("Duplicate found for criterionType: " + config.getCriterionType());
                }

                if (config instanceof LengthConfig) {
                    LengthConfig lengthConfig = (LengthConfig) config;
                    if (lengthConfig.getLength() <= 0) {
                        throw new IllegalArgumentException("Length must be greater than 0 for config: " + config.getCriterionType());
                    }
                }

                if (config instanceof BirthdateConfig) {
                    BirthdateConfig birthdateConfig = (BirthdateConfig) config;
                    var dateFormat = birthdateConfig.getDateFormat();
                    if (dateFormat == null || dateFormat.trim().isEmpty()) {
                        throw new IllegalArgumentException("Invalid date format pattern: " + dateFormat);
                    }
                    try {
                        DateTimeFormatter.ofPattern(dateFormat);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid date format pattern: " + dateFormat);
                    }
                }
                // Validate orderIndex
                int orderIndex = config.getOrderIndex();
                if (orderIndex < 1 || orderIndex > 4) {
                    throw new IllegalArgumentException("Invalid orderIndex: " + orderIndex + " should be between 1 and 4");
                }
                if (seenOrderIndexes.contains(orderIndex)) {
                    throw new IllegalArgumentException("Duplicate found for orderIndex: " + orderIndex);
                }
                seenOrderIndexes.add(orderIndex);
            }
        } else {
            throw new IllegalArgumentException("Invalid number of configurations. Expected 4 but got: " + configs.size());
        }
    }

    /**
     * Updates the configuration settings in the database.
     *
     * This method first validates the provided list of configurations using the {@link #validateConfigs(List)} method.
     * It then checks if the `configs` document already exists in the database. If it does, it updates the existing configurations.
     * If the document doesn't exist, it creates a new one and saves the provided configurations.
     *
     * @param listConfigs The list of {@link Config} objects to be updated or created in the database.
     * @return The updated or newly created {@link Configs} document.
     * @throws IllegalArgumentException If the provided list of configurations is invalid.
     */
    @Override
    public Configs updateConfigs(List<Config> listConfigs) {
        validateConfigs(listConfigs);

        Optional<Configs> existingConfigsOptional = repository.findById("configs");
        if (existingConfigsOptional.isPresent()) {
            Configs existingConfigs = existingConfigsOptional.get();
            existingConfigs.setConfigs(listConfigs);
            return repository.save(existingConfigs);
        } else {
            Configs newConfigs = new Configs();
            newConfigs.setConfigs(listConfigs);
            return repository.save(newConfigs);
        }
    }
}
