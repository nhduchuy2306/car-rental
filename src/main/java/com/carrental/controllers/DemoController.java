package com.carrental.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/demo")
@SecurityRequirement(name = "bearerAuth")
public class DemoController {

    private List<String> demoList = List.of("demo1", "demo2", "demo3");
    
    @GetMapping("/hello")
    public ResponseEntity<?> sayHello(){
        return new ResponseEntity<>(demoList, HttpStatus.OK);
    }
}
