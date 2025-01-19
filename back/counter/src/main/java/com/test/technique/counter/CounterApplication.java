package com.test.technique.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Entry point for the Counter application.
 *
 * This is the main class that starts the Spring Boot application. It enables Spring Cloud
 * Discovery Client, which allows this service to register with a service discovery system
 * (e.g., Eureka, Consul) for easier communication between microservices.
 *
 * The application will automatically configure itself based on the application properties
 * and start a Spring Boot web server.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CounterApplication {

	/**
	 * Main method to start the Counter application.
	 *
	 * This method serves as the entry point for the application. It runs the Spring Boot application
	 * by calling the {@link SpringApplication#run(Class, String...)} method, which bootstraps the
	 * application, performs the component scanning, and starts the embedded web server.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CounterApplication.class, args);
	}
}
