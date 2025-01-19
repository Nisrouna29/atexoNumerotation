package com.test.technique.numerotation.controller;

import com.test.technique.model.*;
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

@RestController
@RequestMapping("/generate")
public class NumerotationController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NumerotationService numerotationService;

    @PostMapping
    public ResponseEntity<String> generateNumber(@RequestBody UserInfo userInfo) {

        List<Config> configs = Arrays.asList(
                restTemplate.getForObject("http://CONFIGURATION-SERVICE/configs", Config[].class)
        );
        int counter = restTemplate.postForObject("http://COUNTER-SERVICE/counter/increment", null, Integer.class);

        return ResponseEntity.ok(numerotationService.generate(configs, counter, userInfo));
    }





}