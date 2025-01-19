package com.test.technique.counter.service;

import com.test.technique.counter.document.Counter;
import com.test.technique.counter.repository.CounterRepository;
import com.test.technique.counter.service.impl.CounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CounterServiceTest {

    @Mock
    private CounterRepository repository;

    @InjectMocks
    private CounterService counterService;

    private static final String COUNTER_ID = "global";
    private static final int INITIAL_VALUE = 0;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIncrementCounter_CounterExists() {
        Counter existingCounter = new Counter(COUNTER_ID, 5);
        when(repository.findById(COUNTER_ID)).thenReturn(java.util.Optional.of(existingCounter));

        int newValue = counterService.incrementCounter();
        
        assertEquals(6, newValue);  // Value should increment by 1
        verify(repository).save(existingCounter);  // Verify save was called
        verify(repository, times(1)).findById(COUNTER_ID);  // Verify find was called once
    }

    @Test
    public void testIncrementCounter_CounterDoesNotExist() {
        when(repository.findById(COUNTER_ID)).thenReturn(java.util.Optional.empty());

        int newValue = counterService.incrementCounter();

        assertEquals(INITIAL_VALUE + 1, newValue);  // New counter should start from initial value + 1
        verify(repository).save(any(Counter.class));  // Verify save was called to save the new counter
        verify(repository, times(1)).findById(COUNTER_ID);  // Verify find was called once
    }

    @Test
    public void testGetCurrentCounterValue_CounterExists() {

        Counter existingCounter = new Counter(COUNTER_ID, 10);
        when(repository.findById(COUNTER_ID)).thenReturn(java.util.Optional.of(existingCounter));

        int currentValue = counterService.getCurrentCounterValue();

        assertEquals(10, currentValue);  // Counter should return the existing value
        verify(repository, times(1)).findById(COUNTER_ID);  // Verify find was called once
    }

    @Test
    public void testGetCurrentCounterValue_CounterDoesNotExist() {
        when(repository.findById(COUNTER_ID)).thenReturn(java.util.Optional.empty());

        int currentValue = counterService.getCurrentCounterValue();

        assertEquals(INITIAL_VALUE, currentValue);  // Should return the initial value
        verify(repository, times(1)).findById(COUNTER_ID);  // Verify find was called once
    }
}
