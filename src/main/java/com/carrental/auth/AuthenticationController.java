package com.carrental.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.utils.ResponseUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        var response = authenticationService.register(request);
        return new ResponseEntity<>(
            ResponseUtils.success(response, "Registration successful"),
            HttpStatus.CREATED
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        var authenticateResponse = authenticationService.authenticate(request);
        return new ResponseEntity<>(
            ResponseUtils.success(authenticateResponse, "Authentication successful"),
            HttpStatus.OK
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
        var authenticateResponse = authenticationService.refresh(request);
        return new ResponseEntity<>(
            ResponseUtils.success(authenticateResponse, "Refresh successful"),
            HttpStatus.OK
        );
    }
}