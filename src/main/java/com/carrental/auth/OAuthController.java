package com.carrental.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OAuthController {
//    http://localhost:8080/carrental/login/oauth2/code/google

    private final AuthenticationService authenticationService;

    @GetMapping("/login/oauth2/code/google")
    public Map<String, Object> loginGoogle(OAuth2AuthenticationToken authentication) {
        String email = (String) authentication.getPrincipal().getAttributes().get("email");
        String name  = (String) authentication.getPrincipal().getAttributes().get("name");
        Boolean isVerify = (Boolean) authentication.getPrincipal().getAttributes().get("email_verified");
        String hashCode = (String) authentication.getPrincipal().getAttributes().get("sub");

        var authenticateResponse = authenticationService.loginGoogle(email, name, hashCode);

        log.info("email: {}", email);
        log.info("name: {}", name);
        log.info("isVerify: {}", isVerify);
        log.info("hashCode: {}", hashCode);

        return authentication.getPrincipal().getAttributes();
    }
}
