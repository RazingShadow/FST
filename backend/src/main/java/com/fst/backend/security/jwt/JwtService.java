package com.fst.backend.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final Long expirationInMs = 3600_000L; // 1 hour

    public String generateJwtToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationInMs);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public Boolean validateJwtToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
                    return claims.getExpiration().after(new Date());
        } catch(Exception e) {
            return false;
        }
    }

    public String getUsernameFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = getUsernameFromJwtToken(token);
        // ToDo: load user details from the database
        // Dev: simple Authentication object with the username
        return new UsernamePasswordAuthenticationToken(username, null, null);
    }
}
