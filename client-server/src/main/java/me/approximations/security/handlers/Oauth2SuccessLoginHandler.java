package me.approximations.security.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.approximations.dtos.UserOauthRegisterDTO;
import me.approximations.security.entities.SecurityUserDetails;
import me.approximations.security.jwt.JwtTokenCookieHeaderSetter;
import me.approximations.security.jwt.service.JwtTokenService;
import me.approximations.security.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class Oauth2SuccessLoginHandler implements AuthenticationSuccessHandler {
    private static final RedirectStrategy REDIRECT_STRATEGY = new DefaultRedirectStrategy();

    @Value("${spring.security.oauth2.login-redirect-url}")
    private String successLoginRedirectUrl;
    private final JwtTokenService jwtTokenService;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenCookieHeaderSetter tokenCookieSetter;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByEmail(oidcUser.getEmail());
        } catch (UsernameNotFoundException e) {
            userDetails = new SecurityUserDetails(userDetailsService.createUserOauth(new UserOauthRegisterDTO(oidcUser.getFullName(), oidcUser.getEmail())));
        }

        final String token = jwtTokenService.generateToken(userDetails);

        tokenCookieSetter.set(response, token);

        REDIRECT_STRATEGY.sendRedirect(request, response, successLoginRedirectUrl);
    }
}
