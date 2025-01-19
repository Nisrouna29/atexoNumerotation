package com.test.technique.counter.service.impl;

import com.test.technique.counter.document.Counter;
import com.test.technique.counter.repository.CounterRepository;
import com.test.technique.counter.service.ICounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CounterService implements ICounterService {
    @Autowired
    private CounterRepository repository;

    @Value("${counter.initial-value}")
    private int initialValue;
    private final Lock lock = new ReentrantLock();

    private static final String COUNTER_ID = "global";
    public synchronized int incrementCounter() {
        lock.lock();  // Acquire the lock
        try {
            // Retrieve the counter, or create a new one if not found
            Counter counter = repository.findById(COUNTER_ID)
                    .orElse(new Counter(COUNTER_ID, initialValue));

            // Increment the counter value
            counter.setCurrentValue(counter.getCurrentValue() + 1);

            // Save the updated counter
            repository.save(counter);

            return counter.getCurrentValue();
        } finally {
            lock.unlock();  // Release the lock
        }
    }

    public int getCurrentCounterValue() {
        return repository.findById("global")
                .map(Counter::getCurrentValue)
                .orElse(initialValue);
    }
}

