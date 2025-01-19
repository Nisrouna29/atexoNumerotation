package com.test.technique.counter.repository;

import com.test.technique.counter.document.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing the Counter document in MongoDB.
 *
 * This interface extends {@link MongoRepository} and provides CRUD operations for the Counter
 * collection in MongoDB. It is used to interact with the database and perform operations like
 * saving, finding, and deleting counter data.
 *
 * The repository automatically implements basic MongoDB operations (e.g., findById, save) without
 * needing to write custom queries. This makes it easy to interact with the database using the
 * `Counter` document.
 */
@Repository
public interface CounterRepository extends MongoRepository<Counter, String> {
    // No need for custom methods, as MongoRepository provides basic CRUD functionality
}
