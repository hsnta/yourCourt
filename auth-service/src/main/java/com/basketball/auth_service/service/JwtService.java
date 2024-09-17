package com.basketball.auth_service.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${application.security.jwt.auth.secret-key}")
    private String authSecretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh.secret-key}")
    private String refreshSecretKey;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private final UserDetailsService userDetailsService;

    public String extractUsername(String token, boolean isRefreshToken) {
        return extractClaim(token, Claims::getSubject, isRefreshToken);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, boolean isRefreshToken) {
        final Claims claims = extractAllClaims(token, isRefreshToken);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String username, boolean isRefreshToken) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return generateToken(userDetails, isRefreshToken);
    }

    public String generateToken(UserDetails userDetails, boolean isRefreshToken) {
        return generateToken(new HashMap<>(), userDetails, isRefreshToken);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            boolean isRefreshToken
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration, isRefreshToken);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration,
            boolean isRefreshToken
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(isRefreshToken), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails, boolean isRefreshToken) {
        final String username = extractUsername(token, isRefreshToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token, isRefreshToken);
    }

    private boolean isTokenExpired(String token, boolean isRefreshToken) {
        return extractExpiration(token, isRefreshToken).before(new Date());
    }

    private Date extractExpiration(String token, boolean isRefreshToken) {
        return extractClaim(token, Claims::getExpiration, isRefreshToken);
    }

    private Claims extractAllClaims(String token, boolean isRefreshToken) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey(isRefreshToken))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSignInKey(boolean isRefreshToken) {
        byte[] keyBytes = isRefreshToken ? Decoders.BASE64.decode(refreshSecretKey) : Decoders.BASE64.decode(authSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}