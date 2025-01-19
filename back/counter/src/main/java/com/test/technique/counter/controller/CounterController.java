package com.test.technique.counter.controller;

import com.test.technique.counter.service.ICounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for managing the counter operations.
 *
 * This controller exposes RESTful endpoints to retrieve the current counter value and increment the counter.
 * It interacts with the {@link ICounterService} to perform the necessary operations on the counter.
 *
 * The following endpoints are available:
 * 1. {@link #getCurrentCounterValue()} - Get the current counter value.
 * 2. {@link #incrementCounter()} - Increment the counter value by 1.
 */
@RestController
@RequestMapping("/counter")
public class CounterController {

    @Autowired
    private ICounterService counterService;

    /**
     * Retrieves the current value of the counter.
     *
     * This GET API returns the current value of the counter managed by the service. It calls the
     * {@link ICounterService#getCurrentCounterValue()} method to get the value.
     *
     * @return A {@link ResponseEntity} containing the current counter value in the body
     *         and an HTTP status of {@link HttpStatus#OK} (200).
     */
    @GetMapping("/current")
    public ResponseEntity<Integer> getCurrentCounterValue() {
        int currentValue = counterService.getCurrentCounterValue();
        return new ResponseEntity<>(currentValue, HttpStatus.OK);
    }

    /**
     * Increments the counter value.
     *
     * This POST API increments the current counter value by 1. It interacts with
     * {@link ICounterService#incrementCounter()} to update the counter.
     *
     * @return A {@link ResponseEntity} containing the updated counter value in the body
     *         and an HTTP status of {@link HttpStatus#OK} (200).
     */
    @PostMapping("/increment")
    public ResponseEntity<Integer> incrementCounter() {
        int updatedValue = counterService.incrementCounter();
        return new ResponseEntity<>(updatedValue, HttpStatus.OK);
    }
}
