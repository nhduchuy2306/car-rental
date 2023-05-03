package com.carrental.configs;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiecImpl implements JwtService{

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    @Value("${JWT_EXPIRATION_TIME_ACCESS_TOKEN}")
    private String expirationTimeForAccessToken;

    @Value("${JWT_EXPIRATION_TIME_REFRESH_TOKEN}")
    private String expirationTimeForRefreshToken;

    @Override
    public String extractUserEmail(String token) {
        var claims = extractAllClaims(token);
        var userMap = claims.get("User", Map.class);
        return userMap.get("email").toString();
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        return generateTokenUtils(new HashMap<>(), userDetails, expirationTimeForAccessToken);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return generateTokenUtils(new HashMap<>(), userDetails, expirationTimeForRefreshToken);
    }

    @Override
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String userEmail = extractUserEmail(token);
            return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        var claims = extractAllClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    private String generateTokenUtils(
        Map<String, Object> extraClaims,
        UserDetails userDetails,
        String expirationTimeType
    ){
        extraClaims.put("User", userDetails);

        return Jwts.builder()
            .setClaims(extraClaims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationTimeType)))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Claims extractAllClaims(String token) throws SecurityException {
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
