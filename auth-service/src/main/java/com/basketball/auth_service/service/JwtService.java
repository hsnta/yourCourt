package com.basketball.auth_service.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

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

    public String extractUsername(String token, boolean isRefreshToken) {
        return extractClaim(token, Claims::getSubject, isRefreshToken);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, boolean isRefreshToken) {
        final Claims claims = extractAllClaims(token, isRefreshToken);
        return claimsResolver.apply(claims);
    }


    public String generateToken(Map<String, Object> extraClaims, String username, boolean isRefreshToken) {
        return buildToken(extraClaims, username, jwtExpiration, isRefreshToken);
    }

    private String buildToken(Map<String, Object> extraClaims, String userName,
                              long expiration, boolean isRefreshToken) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(isRefreshToken), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, boolean isRefreshToken, long allowedCounterVal) {
        return !isTokenExpired(token, isRefreshToken) && extractClaim(token, claims -> (int) (claims.get("ctr")), isRefreshToken) >= allowedCounterVal;
    }

    private boolean isTokenExpired(String token, boolean isRefreshToken) {
        return extractExpiration(token, isRefreshToken).before(new Date());
    }

    private Date extractExpiration(String token, boolean isRefreshToken) {
        return extractClaim(token, Claims::getExpiration, isRefreshToken);
    }

    private Claims extractAllClaims(String token, boolean isRefreshToken) {
        return Jwts.parser()
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