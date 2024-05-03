package me.approximations.security.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Primary
public class JwtTokenServiceImpl implements JwtTokenService {
    private static final String ISSUER = "client-server";
    private final Algorithm algorithm;
    private final Long expireSeconds;

    public JwtTokenServiceImpl(@Value("${spring.security.jwt.secret}") String secretKey,
                               @Value("${spring.security.jwt.expire_seconds}") Long expireSeconds) {
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.expireSeconds = expireSeconds;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(defaultExpiresAt())
                    .withSubject(userDetails.getUsername())
                    .sign(this.algorithm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DecodedJWT decodeToken(String token) throws JWTVerificationException {
        return JWT.require(this.algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token);
    }

    private Instant defaultExpiresAt() {
        return Instant.now().plusSeconds(expireSeconds);
    }
}
