package com.test.technique.numerotation.service;

import com.test.technique.model.*;
import com.test.technique.numerotation.model.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumerotationServiceTest {

    private NumerotationService numerotationService;
    private UserInfo userInfo;
    private List<Config> configs;

    @BeforeEach
    void setUp() {
        numerotationService = new NumerotationService();

        // Setting up UserInfo
        userInfo = new UserInfo();
        userInfo.setFirstname("John");
        userInfo.setLastname("Doe");
        userInfo.setBirthdate(LocalDate.of(1990, 1, 1));

        // Creating Configs
        FirstNameConfig firstNameConfig = new FirstNameConfig();
        firstNameConfig.setCriterionType("FIRSTNAME");
        firstNameConfig.setOrderIndex(1);
        firstNameConfig.setPrefix("FN-");
        firstNameConfig.setSuffix("-");
        firstNameConfig.setLength(4);

        NameConfig nameConfig = new NameConfig();
        nameConfig.setCriterionType("NAME");
        nameConfig.setOrderIndex(2);
        nameConfig.setPrefix("LN-");
        nameConfig.setSuffix("-");
        nameConfig.setLength(3);

        BirthdateConfig birthdateConfig = new BirthdateConfig();
        birthdateConfig.setCriterionType("BIRTHDATE");
        birthdateConfig.setOrderIndex(3);
        birthdateConfig.setPrefix("BD-");
        birthdateConfig.setSuffix("-");
        birthdateConfig.setDateFormat("yyyyMMdd");

        CounterConfig counterConfig = new CounterConfig();
        counterConfig.setCriterionType("COUNTER");
        counterConfig.setOrderIndex(4);
        counterConfig.setPrefix("C");
        counterConfig.setLength(3);

        // List of Configs to be passed to the service
        configs = Arrays.asList(firstNameConfig, nameConfig, birthdateConfig, counterConfig);
    }

    @Test
    void generate_ValidInput_ReturnsCorrectlyFormattedNumber() {
        String result = numerotationService.generate(configs, 1, userInfo);
        assertEquals("FN-JOHN-LN-DOE-BD-19900101-C001", result);
    }




    @Test
    void generate_WithCounterValue_ReturnsCorrectlyFormattedCounter() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstname("John");
        userInfo.setLastname("Doe");
        userInfo.setBirthdate(LocalDate.of(1990, 1, 1));

        FirstNameConfig firstNameConfig = new FirstNameConfig();
        firstNameConfig.setCriterionType("FIRSTNAME");
        firstNameConfig.setOrderIndex(1);
        firstNameConfig.setPrefix("FN-");
        firstNameConfig.setLength(4);

        NameConfig nameConfig = new NameConfig();
        nameConfig.setCriterionType("NAME");
        nameConfig.setOrderIndex(2);
        nameConfig.setPrefix("LN-");
        nameConfig.setLength(3);

        BirthdateConfig birthdateConfig = new BirthdateConfig();
        birthdateConfig.setCriterionType("BIRTHDATE");
        birthdateConfig.setOrderIndex(3);
        birthdateConfig.setPrefix("BD-");
        birthdateConfig.setDateFormat("yyyyMMdd");

        CounterConfig counterConfig = new CounterConfig();
        counterConfig.setCriterionType("COUNTER");
        counterConfig.setOrderIndex(4);
        counterConfig.setPrefix("C");
        counterConfig.setLength(3);

        List<Config> configs = Arrays.asList(firstNameConfig, nameConfig, birthdateConfig, counterConfig);

        String result = numerotationService.generate(configs, 99, userInfo);

        assertEquals("FN-JOHNLN-DOEBD-19900101C099", result); // Counter is formatted with leading zeros
    }


    @Test
    void generate_ValidConfigWithTruncation_ReturnsCorrectlyFormattedNumber() {
        // Arrange: Test with name length truncation
        userInfo.setFirstname("Jonathan");
        userInfo.setLastname("Johnson");

        // Act
        String result = numerotationService.generate(configs, 1, userInfo);

        // Assert
        assertEquals("FN-JONA-LN-JOH-BD-19900101-C001", result);  // Truncated names
    }

    @Test
    void generate_ShortNameWithPadding_ReturnsCorrectlyFormattedNumber() {
        userInfo.setFirstname("Jo");
        userInfo.setLastname("Do");


        String result = numerotationService.generate(configs, 1, userInfo);

        assertEquals("FN-JO__-LN-DO_-BD-19900101-C001", result);
    }

    @Test
    void generate_WithEmptyConfigs_ReturnsEmptyString() {

        List<Config> emptyConfigs = Arrays.asList();

        String result = numerotationService.generate(emptyConfigs, 1, userInfo);

        assertEquals("", result);
    }

    @Test
    void generate_WithNullUserInfo_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            numerotationService.generate(configs, 1, null);
        });
    }

    @Test
    void validateUserInfo_WithValidUserInfo_DoesNotThrowException() {
        // No exception should be thrown for valid userInfo
        assertDoesNotThrow(() -> numerotationService.generate(configs, 1, userInfo));
    }

    @Test
    void validateUserInfo_WithNullUserInfo_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            numerotationService.generate(configs, 1, null);
        });
    }

    @Test
    void validateUserInfo_WithNullFirstname_ThrowsException() {
        userInfo.setFirstname(null);
        assertThrows(IllegalArgumentException.class, () -> {
            numerotationService.generate(configs, 1, userInfo);
        });
    }

    @Test
    void validateUserInfo_WithEmptyFirstname_ThrowsException() {
        userInfo.setFirstname("  "); // whitespace only
        assertThrows(IllegalArgumentException.class, () -> {
            numerotationService.generate(configs, 1, userInfo);
        });
    }

    @Test
    void validateUserInfo_WithNullLastname_ThrowsException() {
        userInfo.setLastname(null);
        assertThrows(IllegalArgumentException.class, () -> {
            numerotationService.generate(configs, 1, userInfo);
        });
    }

    @Test
    void validateUserInfo_WithEmptyLastname_ThrowsException() {
        userInfo.setLastname("  "); // whitespace only
        assertThrows(IllegalArgumentException.class, () -> {
            numerotationService.generate(configs, 1, userInfo);
        });
    }

    @Test
    void validateUserInfo_WithNullBirthdate_ThrowsException() {
        userInfo.setBirthdate(null);
        assertThrows(IllegalArgumentException.class, () -> {
            numerotationService.generate(configs, 1, userInfo);
        });
    }


}
