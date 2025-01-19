package com.test.technique.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Main entry point for the Gateway application.
 * <p>
 * This class initializes and starts the Spring Boot application for the Gateway service.
 * It is also annotated with {@link EnableDiscoveryClient} to enable service discovery, allowing this application
 * to be discovered and interact with other microservices within the ecosystem.
 * The {@link EnableWebFlux} annotation enables support for WebFlux, which is a non-blocking, reactive web framework.
 * </p>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableWebFlux
public class GatewayApplication {

	/**
	 * The main method to launch the Gateway service.
	 * <p>
	 * This method runs the Spring Boot application, setting up the necessary configurations, and starting the
	 * embedded server to handle incoming requests. It uses {@link SpringApplication#run(Class, String[])}
	 * to bootstrap the application.
	 * </p>
	 *
	 * @param args Command-line arguments passed to the application during startup.
	 */
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
