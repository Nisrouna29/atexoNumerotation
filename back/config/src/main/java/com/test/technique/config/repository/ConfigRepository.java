package com.test.technique.config.repository;

import com.test.technique.config.document.Configs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing the {@link Configs} collection in MongoDB.
 *
 * This interface extends {@link MongoRepository}, which provides CRUD operations for the {@link Configs} document.
 * It allows for easy management of {@link Configs} entities in MongoDB without needing to write custom queries.
 * The repository is automatically implemented by Spring Data MongoDB, allowing for seamless data interaction.
 *
 * {@link ConfigRepository} can be injected into service classes to perform operations on the `configs` collection.
 */
@Repository
public interface ConfigRepository extends MongoRepository<Configs, String> {
    // No additional methods are needed as MongoRepository provides basic CRUD operations.
}
