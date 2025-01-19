package com.test.technique.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main entry point for the Spring Boot application.
 *
 * This class is responsible for bootstrapping the Spring Boot application, which is part of the configuration service.
 * It enables the necessary configurations and registers the application as a client with the service discovery system
 * using {@link EnableDiscoveryClient}. This allows the service to be discovered by other microservices in a cloud-native
 * environment (e.g., via Eureka, Consul, or other service discovery platforms).
 *
 * {@link ConfigApplication} is the entry point for starting the application and running the Spring Boot context.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigApplication {

	/**
	 * Main method to launch the Spring Boot application.
	 *
	 * This method runs the Spring Boot application by invoking the {@link SpringApplication#run(Class, String[])}
	 * method, which initializes the Spring context and starts the embedded web server (if applicable).
	 *
	 * @param args The command-line arguments passed to the application. They can be used to customize the application
	 *             or pass environment-specific properties.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}
