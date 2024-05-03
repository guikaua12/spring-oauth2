package me.approximations.services.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {
    String generateToken(UserDetails userDetails);

    DecodedJWT decodeToken(String token) throws JWTVerificationException;

}
