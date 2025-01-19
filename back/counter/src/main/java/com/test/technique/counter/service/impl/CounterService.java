package com.test.technique.counter.service.impl;

import com.test.technique.counter.document.Counter;
import com.test.technique.counter.repository.CounterRepository;
import com.test.technique.counter.service.ICounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class CounterService implements ICounterService {
    @Autowired
    private CounterRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${counter.initial-value}")
    private int initialValue;

    public int incrementCounter() {
        Counter counter = repository.findById("global")
                .orElse(new Counter(initialValue));
        counter.setCurrentValue(counter.getCurrentValue() + 1);
        repository.save(counter);
        return counter.getCurrentValue();
    }

    public int getCurrentCounterValue() {
        return repository.findById("global")
                .map(Counter::getCurrentValue)
                .orElse(initialValue);
    }
}

