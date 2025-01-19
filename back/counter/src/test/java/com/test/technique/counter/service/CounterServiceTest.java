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
    private CounterRepository counterRepository; // Mock the repository

    @InjectMocks
    private CounterService counterService; // The service under test

    private Counter counter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        counter = new Counter(0);
        counter.setId("global");
    }


    @Test
    public void testIncrementCounter_WhenCounterExists() {
        when(counterRepository.findById("global")).thenReturn(java.util.Optional.of(counter));
        int result = counterService.incrementCounter();
        assertEquals(1, result);
        verify(counterRepository, times(1)).save(counter);
    }

    @Test
    public void testIncrementCounter_WhenCounterDoesNotExist() {
        when(counterRepository.findById("global")).thenReturn(java.util.Optional.empty());
        int result = counterService.incrementCounter();
        assertEquals(1, result);
        verify(counterRepository, times(1)).save(any(Counter.class));  // Ensure save was called once with any Counter
    }

    @Test
    public void testGetCurrentCounterValue_WhenCounterExists() {
        when(counterRepository.findById("global")).thenReturn(java.util.Optional.of(counter));
        int result = counterService.getCurrentCounterValue();
        assertEquals(0, result);
    }
    @Test
    public void testGetCurrentCounterValue_WhenCounterDoesNotExist() {
        when(counterRepository.findById("global")).thenReturn(java.util.Optional.empty());
        int result = counterService.getCurrentCounterValue();
        assertEquals(0, result);
    }
}
