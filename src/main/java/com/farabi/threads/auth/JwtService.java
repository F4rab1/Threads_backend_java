package com.farabi.threads.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
class JwtService {
    public String generateToken(String email) {
        final long tokenExpiration = 86400;

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .signWith(Keys.hmacShaKeyFor("3RVGNq9Dl/rkK26de0/9t5ag4/nMKjYFR0fEWwMrtfE=".getBytes()))
                .compact();
    }
}
