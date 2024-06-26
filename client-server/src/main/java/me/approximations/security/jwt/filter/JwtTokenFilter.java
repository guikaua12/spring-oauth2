package me.approximations.security.jwt.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.approximations.security.entities.SecurityUserDetails;
import me.approximations.security.handlers.CustomAuthenticationEntryPoint;
import me.approximations.security.jwt.service.JwtTokenService;
import me.approximations.security.jwt.token.JwtAuthenticationToken;
import me.approximations.security.services.CustomUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final CustomUserDetailsService userDetailsService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final Cookie cookie = WebUtils.getCookie(request, "token");

        if (cookie == null || cookie.getValue().isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = cookie.getValue();

        try {
            final DecodedJWT jwt = jwtTokenService.decodeToken(token);
            final SecurityUserDetails userDetails = userDetailsService.loadUserByEmail(jwt.getSubject());

            final Authentication authentication = new JwtAuthenticationToken(userDetails, true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException | UsernameNotFoundException e) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response);
        }
    }
}
