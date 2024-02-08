package com.example.fingoal.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JWTService {
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    String generateRefreshToken(
            UserDetails userDetails
    );
}
