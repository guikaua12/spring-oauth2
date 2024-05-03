package me.approximations.security.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.approximations.security.entities.SecurityUserDetails;
import me.approximations.security.handlers.CustomAuthenticationEntryPoint;
import me.approximations.security.services.CustomUserDetailsService;
import me.approximations.security.token.JwtAuthenticationToken;
import me.approximations.services.jwt.JwtTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenService jwtTokenService;
    private final CustomUserDetailsService userDetailsService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.replace(BEARER_PREFIX, "");

        if (token.isEmpty()) {
            authenticationEntryPoint.commence(request, response);
            return;
        }

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
