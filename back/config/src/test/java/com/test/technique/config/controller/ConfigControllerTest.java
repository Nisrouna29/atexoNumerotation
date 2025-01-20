package com.test.technique.config.controller;

import com.test.technique.config.document.Configs;
import com.test.technique.config.service.impl.ConfigService;
import com.test.technique.model.Config;
import com.test.technique.model.FirstNameConfig;
import com.test.technique.model.NameConfig;
import com.test.technique.model.BirthdateConfig;
import com.test.technique.model.CounterConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConfigControllerTest {

    @Mock
    private ConfigService configService;

    @InjectMocks
    private ConfigController configController;

    private List<Config> validConfigs;
    private Configs configsDocument;

    @BeforeEach
    void setUp() {
        // Create valid configurations
        validConfigs = new ArrayList<>();

        FirstNameConfig firstNameConfig = new FirstNameConfig();
        firstNameConfig.setCriterionType("FIRSTNAME");
        firstNameConfig.setOrderIndex(1);
        firstNameConfig.setLength(4);

        NameConfig nameConfig = new NameConfig();
        nameConfig.setCriterionType("NAME");
        nameConfig.setOrderIndex(2);
        nameConfig.setLength(3);

        BirthdateConfig birthdateConfig = new BirthdateConfig();
        birthdateConfig.setCriterionType("BIRTHDATE");
        birthdateConfig.setOrderIndex(3);
        birthdateConfig.setDateFormat("yyyyMMdd");

        CounterConfig counterConfig = new CounterConfig();
        counterConfig.setCriterionType("COUNTER");
        counterConfig.setOrderIndex(4);
        counterConfig.setLength(3);

        validConfigs.add(firstNameConfig);
        validConfigs.add(nameConfig);
        validConfigs.add(birthdateConfig);
        validConfigs.add(counterConfig);

        configsDocument = new Configs();
        configsDocument.setConfigs(validConfigs);
    }

    @Test
    void testCreateConfigs_Success() {
        when(configService.updateConfigs(validConfigs)).thenReturn(configsDocument);
        ResponseEntity<String> response = configController.createConfigs(validConfigs);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Configs Updated successfully", response.getBody());
        verify(configService, times(1)).updateConfigs(validConfigs);
    }

    @Test
    void testCreateConfigs_Failure() {
        List<Config> emptyConfigs = new ArrayList<>();
        when(configService.updateConfigs(emptyConfigs)).thenReturn(null);
        ResponseEntity<String> response = configController.createConfigs(emptyConfigs);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to update configs", response.getBody());
        verify(configService, times(1)).updateConfigs(emptyConfigs);
    }

    @Test
    void testGetAllConfigs_Success() {
        when(configService.getAllConfigs()).thenReturn(validConfigs);
        ResponseEntity<List<Config>> response = configController.getAllConfigs();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(4, response.getBody().size());
        verify(configService, times(1)).getAllConfigs();
    }

    @Test
    void testGetAllConfigs_NoContent() {
        when(configService.getAllConfigs()).thenReturn(new ArrayList<>()); // Empty list
        ResponseEntity<List<Config>> response = configController.getAllConfigs();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(configService, times(1)).getAllConfigs();
    }
}
