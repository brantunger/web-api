package com.dreadfall.webapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class JwtTokenService {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${security.secret}") final String secret) {
        this.algorithm = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(algorithm).build();
    }

    public String generateToken(final UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.DAYS))
                .sign(algorithm);
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException verificationEx) {
            log.warn("Token invalid: {}", verificationEx.getMessage());
            return null;
        }
    }
}
