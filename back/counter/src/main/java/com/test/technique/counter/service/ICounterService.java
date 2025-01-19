package com.test.technique.counter.service;

/**
 * Interface for Counter Service.
 *
 * This interface defines the contract for services that manage a counter. It provides methods
 * for incrementing the counter and retrieving the current value of the counter.
 * The implementation of this interface is expected to handle counter operations in a thread-safe
 * manner and interact with the underlying data store (e.g., MongoDB).
 */
public interface ICounterService {

    /**
     * Increment the counter value by 1.
     *
     * This method is responsible for increasing the current counter value stored in the data store.
     * The counter value should be updated atomically to ensure consistency in a multi-threaded environment.
     *
     * @return The updated counter value after incrementing.
     */
    public int incrementCounter();

    /**
     * Retrieve the current value of the counter.
     *
     * This method retrieves the current value of the counter from the data store. If the counter does
     * not exist, it may return a predefined initial value or handle the absence of the counter in an
     * appropriate manner (e.g., initializing it).
     *
     * @return The current counter value.
     */
    public int getCurrentCounterValue();
}
