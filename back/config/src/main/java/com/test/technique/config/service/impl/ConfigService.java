package com.test.technique.config.service.impl;

import com.test.technique.config.document.Configs;

import com.test.technique.config.repository.ConfigRepository;
import com.test.technique.config.service.IConfigService;
import com.test.technique.model.Config;
import com.test.technique.model.CriterionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConfigService implements IConfigService {
    @Autowired
    private ConfigRepository repository;

    @Override
    public List<Config> getAllConfigs() {
        Configs configs = repository.findById("configs").orElse(null);
        return (configs != null) ? configs.getConfigs() : Collections.emptyList();
    }

    public void validateConfigs(List<Config> configs) {
        if (configs.size() == 4) {
            Set<Integer> seenOrderIndexes = new HashSet<>();
            for (Config config : configs) {
                if (config.getCriterionType() == null || !CriterionType.isValid(config.getCriterionType())) {
                    throw new IllegalArgumentException("Invalid criterionType: " + config.getCriterionType());
                }
                int orderIndex = config.getOrderIndex();

                if (orderIndex < 1 || orderIndex > 4) {
                    throw new IllegalArgumentException("Invalid orderIndex: " + orderIndex + " should be between 1 and 4");
                }
                if (seenOrderIndexes.contains(orderIndex)) {
                    throw new IllegalArgumentException("Duplicate found for orderIndex: " + orderIndex);
                }
                seenOrderIndexes.add(orderIndex);
            }
        } else {
            throw new IllegalArgumentException("Invalid number of configurations. Expected 4 but got: " + configs.size());
        }
    }


    @Override
    public Configs updateConfigs(List<Config> listConfigs) {
        validateConfigs(listConfigs);

        Optional<Configs> existingConfigsOptional = repository.findById("configs");
        if (existingConfigsOptional.isPresent()) {
            Configs existingConfigs = existingConfigsOptional.get();
            existingConfigs.setConfigs(listConfigs);
            return repository.save(existingConfigs);
        } else {
            Configs Configs = new Configs();
            Configs.setConfigs(listConfigs);
            return repository.save(Configs);
        }

    }
}
