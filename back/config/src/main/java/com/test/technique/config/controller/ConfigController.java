package com.test.technique.config.controller;

import com.test.technique.config.document.Configs;
import com.test.technique.config.service.impl.ConfigService;
import com.test.technique.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configs")
public class ConfigController {

    @Autowired
    private ConfigService configService;
    @GetMapping
    public ResponseEntity<List<Config>> getAllConfigs() {
        List<Config> configs = configService.getAllConfigs();
        if (configs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(configs, HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<String> createConfigs(@RequestBody List<Config> configs) {
        Configs createdConfigs = configService.updateConfigs(configs);
        if (createdConfigs == null || configs.isEmpty()) {
            return new ResponseEntity<>("Failed to update configs", HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
        return new ResponseEntity<>("Configs Updated successfully", HttpStatus.CREATED); // 201 Created
    }

}
