package com.carrental.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/first")
public class FirstController {

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping("/profile")
    public String getProfile() {
        return "Hello from FirstController from port: " + profile + "";
    }
}
