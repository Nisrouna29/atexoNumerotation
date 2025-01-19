package com.test.technique.counter.controller;

import com.test.technique.counter.service.ICounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CounterControllerTest {

    @Mock
    private ICounterService counterService;

    @InjectMocks
    private CounterController counterController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrentCounterValue() {
        int expectedValue = 10;
        when(counterService.getCurrentCounterValue()).thenReturn(expectedValue);
        ResponseEntity<Integer> response = counterController.getCurrentCounterValue();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedValue, response.getBody());
        verify(counterService, times(1)).getCurrentCounterValue();  // Verifies that the method was called once
    }

    @Test
    void testIncrementCounter() {
        int incrementedValue = 11;
        when(counterService.incrementCounter()).thenReturn(incrementedValue);
        ResponseEntity<Integer> response = counterController.incrementCounter();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(incrementedValue, response.getBody());
        verify(counterService, times(1)).incrementCounter();  // Verifies that the method was called once
    }
}

