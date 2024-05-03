package me.approximations.security.jwt;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenCookieHeaderSetter {
    public void set(HttpServletResponse response, String token) {
        response.setHeader(HttpHeaders.SET_COOKIE, String.format("token=%s; Max-Age=86400; Path=/", token));
    }
}
