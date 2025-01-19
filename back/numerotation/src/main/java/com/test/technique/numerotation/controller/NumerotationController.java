package com.test.technique.numerotation.controller;

import com.test.technique.model.Config;
import com.test.technique.numerotation.model.UserInfo;
import com.test.technique.numerotation.service.NumerotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Controller for generating a numerotation based on user information and system configurations.
 *
 * This controller is responsible for accepting a `POST` request to generate a numerotation
 * using the provided `UserInfo` object. It fetches configuration settings and increments the
 * counter by interacting with other services (Configuration Service and Counter Service).
 */
@RestController
@RequestMapping("/generate")
public class NumerotationController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NumerotationService numerotationService;

    /**
     * Handles the generation of a numerotation.
     *
     * This method receives a `UserInfo` object from the request body, fetches the system configurations
     * from the Configuration Service, and increments the counter using the Counter Service. It then
     * delegates the generation of the numerotation to the `NumerotationService` based on the fetched
     * configurations and counter value.
     *
     * @param userInfo The user information provided in the request body to generate the numerotation.
     * @return A `ResponseEntity` containing the generated numerotation as a string.
     */
    @PostMapping
    public ResponseEntity<String> generateNumber(@RequestBody UserInfo userInfo) {

        // Fetching configuration details from the Configuration Service
        List<Config> configs = Arrays.asList(
                restTemplate.getForObject("http://CONFIGURATION-SERVICE/configs", Config[].class)
        );

        // Incrementing the counter by calling the Counter Service
        int counter = restTemplate.postForObject("http://COUNTER-SERVICE/counter/increment", null, Integer.class);

        // Generating the numerotation using NumerotationService and returning the result
        return ResponseEntity.ok(numerotationService.generate(configs, counter, userInfo));
    }
}
