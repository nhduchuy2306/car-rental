package com.carrental.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.dtos.request.LoginRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class FirstController {

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping("/profile")
    @Operation(
        description = "Get profile",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", ref = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", ref = "BadRequest"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", ref = "InternalServerError")
        }
    )
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok("Hello from FirstController from port: " + profile + "");
    }

    @PostMapping("/profile")
    public ResponseEntity<?> createProfile(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Login Request", required = true, content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = LoginRequestDto.class)))
        @RequestBody LoginRequestDto loginRequestDto
    ) {
        return ResponseEntity.ok("Hello from FirstController from port: " + profile + "");
    }
}
