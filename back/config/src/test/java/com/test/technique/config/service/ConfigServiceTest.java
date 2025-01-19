package com.test.technique.config.service;

import com.test.technique.config.document.Configs;
import com.test.technique.config.repository.ConfigRepository;
import com.test.technique.config.service.impl.ConfigService;
import com.test.technique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfigServiceTest {

    @Mock
    private ConfigRepository repository;

    @InjectMocks
    private ConfigService configService;

    private List<Config> validConfigs;
    private Configs configsDocument;

    @BeforeEach
    void setUp() {
        validConfigs = new ArrayList<>();

        // Add valid configuration objects
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

        // Create Configs document
        configsDocument = new Configs();
        configsDocument.setConfigs(validConfigs);
    }

    @Test
    void getAllConfigs_WhenConfigsExist_ReturnsConfigs() {
        when(repository.findById("configs")).thenReturn(Optional.of(configsDocument));

        List<Config> result = configService.getAllConfigs();

        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("FIRSTNAME", result.get(0).getCriterionType());
        assertEquals("NAME", result.get(1).getCriterionType());
        assertEquals("BIRTHDATE", result.get(2).getCriterionType());
        assertEquals("COUNTER", result.get(3).getCriterionType());
    }

    @Test
    void getAllConfigs_WhenNoConfigsExist_ReturnsEmptyList() {
        when(repository.findById("configs")).thenReturn(Optional.empty());

        List<Config> result = configService.getAllConfigs();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void createConfigs_WithValidNewConfigs_SavesConfigs() {
        when(repository.findById("configs")).thenReturn(Optional.empty());
        when(repository.save(any(Configs.class))).thenReturn(configsDocument);

        Configs result = configService.updateConfigs(validConfigs);

        assertNotNull(result);
        assertEquals(4, result.getConfigs().size());
    }

    @Test
    void createConfigs_WithValidExistingConfigs_UpdatesConfigs() {
        when(repository.findById("configs")).thenReturn(Optional.of(configsDocument));
        when(repository.save(any(Configs.class))).thenReturn(configsDocument);

        Configs result = configService.updateConfigs(validConfigs);

        assertNotNull(result);
        assertEquals(4, result.getConfigs().size());
    }

    @Test
    void validateConfigs_WithInvalidSize_ThrowsException() {
        List<Config> invalidSizeConfigs = new ArrayList<>(validConfigs.subList(0, 3));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(invalidSizeConfigs)
        );
        assertEquals("Invalid number of configurations. Expected 4 but got: 3", exception.getMessage());
    }

    @Test
    void validateConfigs_WithInvalidCriterionType_ThrowsException() {
        FirstNameConfig invalidConfig = new FirstNameConfig();
        invalidConfig.setCriterionType("INVALID_TYPE");
        validConfigs.set(0, invalidConfig);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );
        assertEquals("Invalid criterionType: INVALID_TYPE", exception.getMessage());
    }

    @Test
    void validateConfigs_WithNullCriterionType_ThrowsException() {
        FirstNameConfig invalidConfig = new FirstNameConfig();
        invalidConfig.setCriterionType(null);
        validConfigs.set(0, invalidConfig);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );
        assertEquals("Invalid criterionType: null", exception.getMessage());
    }

    @Test
    void validateConfigs_WithDuplicateCriterionType_ThrowsException() {
        // Create a duplicate criterionType in the configurations
        FirstNameConfig duplicateConfig = new FirstNameConfig();
        duplicateConfig.setCriterionType("FIRSTNAME");
        duplicateConfig.setOrderIndex(5);  // New order index
        duplicateConfig.setLength(4);

        // Replace one of the valid configurations with the duplicate
        validConfigs.set(2, duplicateConfig);

        // Verify that the exception is thrown for duplicate criterionType
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );

        assertEquals("Duplicate found for criterionType: FIRSTNAME", exception.getMessage());
    }

    @Test
    void validateConfigs_WithInvalidOrderIndex_ThrowsException() {
        validConfigs.get(0).setOrderIndex(5);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );
        assertEquals("Invalid orderIndex: 5 should be between 1 and 4", exception.getMessage());
    }

    @Test
    void validateConfigs_WithDuplicateOrderIndex_ThrowsException() {
        validConfigs.get(1).setOrderIndex(1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );
        assertEquals("Duplicate found for orderIndex: 1", exception.getMessage());
    }
    @Test
    void validateConfigs_WithInvalidLength_ThrowsException() {
        // Create a CounterConfig with an invalid length (0)
        CounterConfig invalidConfig = new CounterConfig("COUNTER", 4, 0, "prefix", "suffix");

        validConfigs.set(0, invalidConfig);  // Replace valid config with invalid one

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );

        assertEquals("Length must be greater than 0 for config: COUNTER", exception.getMessage());
    }

    @Test
    void validateConfigs_WithEmptyDateFormat_ThrowsException() {
        BirthdateConfig invalidConfig = new BirthdateConfig();
        invalidConfig.setCriterionType("BIRTHDATE");
        invalidConfig.setOrderIndex(3);
        invalidConfig.setDateFormat("");

        validConfigs.set(2, invalidConfig);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );

        assertEquals("Invalid date format pattern: ", exception.getMessage());
    }


    @Test
    void testInvalidFirstNameConfig() {
        Config invalidConfig = new NameConfig();
        invalidConfig.setCriterionType("FIRSTNAME");
        validConfigs.set(0, invalidConfig);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );
        assertEquals("Config for FIRSTNAME must be an instance of FirstNameConfig.", exception.getMessage());
    }

    @Test
    void validateConfigs_WithInvalidDateFormat_ThrowsException() {
        BirthdateConfig invalidConfig = new BirthdateConfig();
        invalidConfig.setCriterionType("BIRTHDATE");
        invalidConfig.setOrderIndex(3);
        invalidConfig.setDateFormat("INVALID");

        validConfigs.set(2, invalidConfig);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(validConfigs)
        );

        assertEquals("Invalid date format pattern: INVALID", exception.getMessage());
    }


    @Test
    void validateConfigs_WithValidConfigs_PassesValidation() {
        validConfigs.get(0).setOrderIndex(1);
        validConfigs.get(1).setOrderIndex(2);
        validConfigs.get(2).setOrderIndex(3);
        validConfigs.get(3).setOrderIndex(4);

        assertDoesNotThrow(() -> configService.updateConfigs(validConfigs));
    }

    @Test
    void createConfigs_WithInvalidConfigs_ThrowsException() {
        List<Config> invalidConfigs = new ArrayList<>(validConfigs.subList(0, 3));

        assertThrows(
                IllegalArgumentException.class,
                () -> configService.updateConfigs(invalidConfigs)
        );
    }
}
