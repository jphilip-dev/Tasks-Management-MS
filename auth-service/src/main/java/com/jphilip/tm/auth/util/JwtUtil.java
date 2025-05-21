package com.jphilip.tm.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final Key secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret){
        byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken( Long id, String email, List<String> roles){
        return Jwts.builder()
                .subject(email)
                .claim("id", id)
                .claim("roles", String.join(",", roles))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10hrs
                .signWith(secretKey)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
