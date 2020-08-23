package com.sii.cqrs.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Init {

    @GetMapping("/")
    public String index() {
        return "Microservice Query";
    }

}