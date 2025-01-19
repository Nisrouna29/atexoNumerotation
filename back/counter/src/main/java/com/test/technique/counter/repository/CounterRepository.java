package com.test.technique.counter.repository;

import com.test.technique.counter.document.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends MongoRepository<Counter, String> {
}

