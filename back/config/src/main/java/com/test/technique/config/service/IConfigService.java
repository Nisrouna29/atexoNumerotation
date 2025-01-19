package com.test.technique.config.service;

import com.test.technique.config.document.Configs;
import com.test.technique.model.Config;

import java.util.List;

/**
 * Service interface for managing configuration settings.
 *
 * This interface defines the methods for interacting with configuration data.
 * Implementations of this interface should provide business logic for retrieving
 * and updating configuration settings in the system.
 *
 * The primary methods are:
 * 1. {@link #getAllConfigs()} - Retrieve all configurations.
 * 2. {@link #updateConfigs(List)} - Update or create a new configuration document with the given list of configurations.
 */
public interface IConfigService {

    /**
     * Retrieves all configuration settings.
     *
     * This method fetches the list of configurations stored in the system. The implementation should return
     * a list of {@link Config} objects representing the configuration settings.
     *
     * @return A list of {@link Config} objects representing the configuration settings.
     */
    public List<Config> getAllConfigs();

    /**
     * Updates the configuration settings in the system.
     *
     * This method takes a list of {@link Config} objects, validates them, and either updates the existing
     * configuration document in the database or creates a new one if it doesn't exist.
     *
     * @param ListConfigs The list of {@link Config} objects to be saved or updated in the database.
     * @return The updated or newly created {@link Configs} document containing the configuration settings.
     */
    public Configs updateConfigs(List<Config> ListConfigs);
}
