package com.test.technique.config.controller;

import com.test.technique.config.document.Configs;
import com.test.technique.config.service.impl.ConfigService;
import com.test.technique.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing configuration settings.
 * Provides endpoints to get and update configuration data.
 *
 * This controller allows clients to:
 * - Retrieve a list of all configurations.
 * - Update a list of configurations.
 */
@RestController
@RequestMapping("/configs")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * Endpoint to retrieve all configuration settings.
     *
     * @return A {@link ResponseEntity} containing a list of {@link Config} objects and a status code.
     *         If configurations are found, the status is OK (200). If no configurations are found,
     *         the status is NO_CONTENT (204).
     */
    @GetMapping
    public ResponseEntity<List<Config>> getAllConfigs() {
        List<Config> configs = configService.getAllConfigs();
        return new ResponseEntity<>(configs, HttpStatus.OK); // 200 OK
    }

    /**
     * Endpoint to create or update configuration settings.
     *
     * @param configs A list of {@link Config} objects to be updated in the system.
     * @return A {@link ResponseEntity} containing a success or error message along with the corresponding status code.
     *         If the update is successful, the status is CREATED (201). If the update fails, the status is
     *         BAD_REQUEST (400).
     */
    @PutMapping
    public ResponseEntity<String> createConfigs(@RequestBody List<Config> configs) {
        Configs createdConfigs = configService.updateConfigs(configs);
        if (createdConfigs == null || configs.isEmpty()) {
            return new ResponseEntity<>("Failed to update configs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Configs Updated successfully", HttpStatus.CREATED);
    }
}
