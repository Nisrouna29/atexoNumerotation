package com.test.technique.config.service;

import com.test.technique.config.document.Configs;
import com.test.technique.model.Config;

import java.util.List;

public interface IConfigService {
    public List<Config> getAllConfigs();

    public Configs updateConfigs(List<Config> ListConfigs);
}
