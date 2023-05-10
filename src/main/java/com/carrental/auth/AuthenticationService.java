package com.carrental.auth;

import com.carrental.entites.LoginType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carrental.configs.JwtService;
import com.carrental.entites.Role;
import com.carrental.entites.User;
import com.carrental.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .loginType(LoginType.NORMAL)
            .build();
        userRepository.save(user);
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
            .token(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
            .token(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    public AuthenticationResponse refresh(RefreshTokenRequest request) {
        String email = null;
        try {
            email = jwtService.extractUserEmail(request.getRefreshToken());
        } catch (Exception e) {
            throw new RuntimeException("Refresh token is invalid");
        }
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
        Boolean isTokenExpired = jwtService.isTokenValid(request.getRefreshToken(), user);
        if(!isTokenExpired) throw new RuntimeException("Refresh token is expired, Must Login again!");
        var accessToken = jwtService.generateAccessToken(user);
        return AuthenticationResponse.builder()
            .token(accessToken)
            .refreshToken(null)
            .build();
    }

    public AuthenticationResponse loginGoogle(String email, String name, String hashCode) {
        User user = userRepository.findByEmail(email)
            .orElseGet(() -> {
                var newUser = User.builder()
                    .firstname(name)
                    .lastname(name)
                    .email(email)
                    .password(passwordEncoder.encode(hashCode))
                    .role(Role.USER)
                    .loginType(LoginType.GOOGLE)
                    .build();
                userRepository.save(newUser);
                return newUser;
            });
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
            .token(accessToken)
            .refreshToken(refreshToken)
            .build();
    }
}
