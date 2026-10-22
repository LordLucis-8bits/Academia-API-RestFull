package com.academia.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.academia.shared.enums.UserType;

import io.jsonwebtoken.Claims;
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

    public String generateToken(String email, UserType role) {
        return Jwts.builder()
        .subject(email) //guarda o email como "dono" do token
        .claim("role", role.name()) //acresenta role no token                                                  
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION)) //expiração
        .signWith(getSigningKey()) //assinatura
        .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()                            
        .verifyWith(getSigningKey()) //verifica assinatura           
        .build()
        .parseSignedClaims(token) //verifica assinatura e extrai os claims ao mesmo tempo (lança exceção se inválido)             
        .getPayload(); //conteudo dentro do token                       
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isSignatureValid(String token) {
        try {
            getClaims(token); //se não lançar exceção, assinatura é válida
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
    
    public boolean isExpired(String token) {
        Claims claims = getClaims(token); //assume que já foi validado antes
        return claims.getExpiration().before(new Date());
    }
}