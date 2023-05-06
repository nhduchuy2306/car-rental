package com.example.demo.service;

import java.util.Map;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/login")
public class LoginController {
    
    @GetMapping("/google")
    public Map<String, Object> loginGoogle(OAuth2AuthenticationToken authentication) {
        String email = (String) authentication.getPrincipal().getAttributes().get("email");
        String name  = (String) authentication.getPrincipal().getAttributes().get("name");
        Boolean isVerify = (Boolean) authentication.getPrincipal().getAttributes().get("email_verified");
        String hashCode = (String) authentication.getPrincipal().getAttributes().get("sub");

        log.info("email: {}", email);
        log.info("name: {}", name);
        log.info("isVerify: {}", isVerify);
        log.info("hashCode: {}", hashCode);

        return authentication.getPrincipal().getAttributes();
    }
}
