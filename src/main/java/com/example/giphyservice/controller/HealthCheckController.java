package com.example.giphyservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.resource.HttpResource;

@Controller
public class HealthCheckController {

    @GetMapping("healthCheck")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity("Status UP", HttpStatus.OK);
    }


}
