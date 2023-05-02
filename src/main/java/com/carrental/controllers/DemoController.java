package com.carrental.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/demo")
@SecurityRequirement(name = "bearerAuth")
public class DemoController {
    
    @GetMapping("/hello")
    public ResponseEntity<?> sayHello(){
        return ResponseEntity.ok("Hello World");
    }
}
