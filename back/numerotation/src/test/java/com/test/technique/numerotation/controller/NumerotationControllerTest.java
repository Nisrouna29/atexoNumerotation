package com.test.technique.numerotation.controller;

import com.test.technique.model.*;
import com.test.technique.numerotation.model.UserInfo;
import com.test.technique.numerotation.service.NumerotationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NumerotationControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private NumerotationService numerotationService;

    @InjectMocks
    private NumerotationController controller;

    private UserInfo validUserInfo;
    private  Config[] mockConfigs;

    @BeforeEach
    void setUp() {
        // Create a valid UserInfo object for testing
        validUserInfo = new UserInfo();
        validUserInfo.setFirstname("John");
        validUserInfo.setLastname("Doe");
        validUserInfo.setBirthdate(LocalDate.of(1990, 1, 1));

        // Set up mock configuration
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


       mockConfigs = new Config[]{firstNameConfig, nameConfig, birthdateConfig, counterConfig};
    }

    @Test
    void generateNumber_ValidInput_ReturnsCorrectFormat() {
        // Mock the external service calls
        when(restTemplate.getForObject(anyString(), eq(Config[].class)))
                .thenReturn(mockConfigs);
        when(restTemplate.postForObject(anyString(), any(), eq(Integer.class)))
                .thenReturn(99);
        when(numerotationService.generate(eq(Arrays.asList(mockConfigs)), eq(99), eq(validUserInfo)))
                .thenReturn("FN-JOHN-LN-DOE-BD-19900101-C099");

        // Act
        ResponseEntity<String> response = controller.generateNumber(validUserInfo);

        // Assert
        assertEquals("FN-JOHN-LN-DOE-BD-19900101-C099", response.getBody());
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }


    @Test
    void generateNumber_WithMissingConfig_ReturnsEmptyResult() {
        when(restTemplate.getForObject(anyString(), eq(Config[].class)))
                .thenReturn(new Config[0]);
        when(restTemplate.postForObject(anyString(), any(), eq(Integer.class)))
                .thenReturn(99);

        ResponseEntity<String> response = controller.generateNumber(validUserInfo);

        assertEquals(null, response.getBody());  // Expect an empty result if no config is available
    }
}
