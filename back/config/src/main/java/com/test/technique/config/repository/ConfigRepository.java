package com.test.technique.config.repository;


import com.test.technique.config.document.Configs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfigRepository extends MongoRepository<Configs, String> {
}
