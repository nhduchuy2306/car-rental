package com.carrental.configs;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserEmail(String token);
    Boolean isTokenValid(String token, UserDetails userDetails);
    String generateAccessToken(UserDetails userDetails);
    String generateRefreshToken(UserDetails userDetails);
}
 