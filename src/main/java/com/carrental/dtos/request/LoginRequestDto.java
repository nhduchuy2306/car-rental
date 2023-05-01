package com.carrental.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"username", "password"})
public class LoginRequestDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
