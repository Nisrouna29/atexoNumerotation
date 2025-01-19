package com.test.technique.counter.service.impl;

import com.test.technique.counter.document.Counter;
import com.test.technique.counter.repository.CounterRepository;
import com.test.technique.counter.service.ICounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for managing and updating the counter value.
 *
 * This service provides functionality for incrementing the counter and retrieving its current value.
 * The counter's value is persisted in the `counter` collection in MongoDB, where the counter document
 * is identified by the ID "global". The service ensures that the counter is thread-safe and can
 * handle concurrent requests by using synchronized methods.
 */
@Service
public class CounterService implements ICounterService {

    @Autowired
    private CounterRepository repository;

    /**
     * The initial value for the counter, which is configured via application properties.
     */
    @Value("${counter.initial-value}")
    private int initialValue;

    /**
     * Increment the counter value by 1.
     *
     * This method retrieves the current counter value from the MongoDB collection. If the counter
     * does not exist, it initializes the counter with the configured initial value. The counter's
     * value is then incremented by 1 and saved back to the database. This operation is synchronized
     * to ensure thread safety.
     *
     * @return The updated counter value after incrementing.
     */
    public synchronized int incrementCounter() {
        // Retrieve the counter from the repository, or create a new one with the initial value
        Counter counter = repository.findById("global")
                .orElse(new Counter(initialValue));

        // Increment the current counter value
        counter.setCurrentValue(counter.getCurrentValue() + 1);

        // Save the updated counter to the repository
        repository.save(counter);

        // Return the updated counter value
        return counter.getCurrentValue();
    }

    /**
     * Get the current counter value.
     *
     * This method retrieves the current value of the counter from the MongoDB collection. If the
     * counter does not exist, the method returns the configured initial value.
     *
     * @return The current counter value.
     */
    public int getCurrentCounterValue() {
        // Retrieve the counter from the repository or return the initial value if not found
        return repository.findById("global")
                .map(Counter::getCurrentValue)
                .orElse(initialValue);
    }
}
