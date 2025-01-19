package com.test.technique.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main entry point for the Eureka Service Registry application.
 * <p>
 * This class sets up and starts the Eureka Service Registry, which enables microservices to register and discover each other
 * in a Spring Cloud-based environment. The application is annotated with {@link EnableEurekaServer} to make it act as a
 * Eureka server for service discovery.
 * </p>
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {

	/**
	 * Main method to run the Eureka Service Registry application.
	 * <p>
	 * This method initializes the Eureka Server, allowing microservices to register with it and enabling them to
	 * discover other registered services. It uses {@link SpringApplication#run(Class, String[])} to launch the application.
	 * </p>
	 *
	 * @param args Command-line arguments passed to the application during startup.
	 */
	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}
}
