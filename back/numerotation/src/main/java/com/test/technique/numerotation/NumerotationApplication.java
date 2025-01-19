package com.test.technique.numerotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for the Numerotation service.
 * <p>
 * This class contains the main method which serves as the entry point for the Spring Boot application.
 * It is annotated with {@link SpringBootApplication} to indicate that this is a Spring Boot application,
 * and {@link EnableDiscoveryClient} to enable this service to register with a service registry (e.g., Eureka).
 * </p>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NumerotationApplication {

	/**
	 * The main method is the entry point of the Spring Boot application.
	 * It launches the application by calling {@link SpringApplication#run(Class, String...)}.
	 *
	 * @param args Command-line arguments (if any).
	 */
	public static void main(String[] args) {
		SpringApplication.run(NumerotationApplication.class, args);
	}
}
