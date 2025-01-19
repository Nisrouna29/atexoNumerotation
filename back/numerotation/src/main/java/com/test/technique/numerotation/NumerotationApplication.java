package com.test.technique.numerotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NumerotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumerotationApplication.class, args);
	}

}
