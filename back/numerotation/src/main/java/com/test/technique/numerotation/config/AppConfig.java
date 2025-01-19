package com.test.technique.numerotation.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Application Configuration class for setting up application-specific beans.
 *
 * This configuration class is responsible for creating and configuring beans that are used
 * across the application. Specifically, it provides a `RestTemplate` bean that is
 * load-balanced by Spring Cloud, enabling the application to make HTTP requests to other
 * services in a distributed environment.
 */
@Configuration
public class AppConfig {

    /**
     * Configures a RestTemplate bean that is load-balanced by Spring Cloud.
     *
     * This bean enables making HTTP requests to other services in a distributed system
     * with load balancing. By using the `@LoadBalanced` annotation, Spring Cloud will
     * automatically handle service discovery and load balancing when using the RestTemplate.
     *
     * @return A load-balanced {@link RestTemplate} instance.
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
