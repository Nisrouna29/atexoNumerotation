package com.test.technique.counter.controller;

import com.test.technique.counter.service.ICounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counter")
public class CounterController {
   @Autowired
    private ICounterService counterService;


    @GetMapping("/current")
    public ResponseEntity<Integer> getCurrentCounterValue() {
        int currentValue = counterService.getCurrentCounterValue();
        return new ResponseEntity<>(currentValue, HttpStatus.OK);
    }

    @PostMapping("/increment")
    public ResponseEntity<Integer> incrementCounter() {
        int updatedValue = counterService.incrementCounter();
        return new ResponseEntity<>(updatedValue, HttpStatus.OK);
    }
}

