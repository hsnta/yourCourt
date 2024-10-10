package com.basketball.video_service.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

import java.util.Date;

@Service
public class TokenService {

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        // Generate a secure key for HS256
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateTemporaryLink(String drillType, long expirationTimeMillis) {
        long expirationTime = System.currentTimeMillis() + expirationTimeMillis;

        // Generate a token that includes the drillType and an expiration date
        return Jwts.builder()
                .setSubject(drillType)
                .setExpiration(new Date(expirationTime))
                .signWith(secretKey) // Use the generated secure key
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder() // Use parserBuilder for better configuration
                    .setSigningKey(secretKey) // Use the generated secure key
                    .build()
                    .parseClaimsJws(token);
            return true; // Token is valid
        } catch (Exception e) {
            return false; // Token is invalid or expired
        }
    }
}