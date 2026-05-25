package com.academia.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
        .subject(email)                                                 
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION)) 
        .signWith(getSigningKey())
        .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()                            
        .verifyWith(getSigningKey())            
        .build()
        .parseSignedClaims(token)               
        .getPayload();                         
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        } catch (JwtException e) {
            return false;
        }
    }
}