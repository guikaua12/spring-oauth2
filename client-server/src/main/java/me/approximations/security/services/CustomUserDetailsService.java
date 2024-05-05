package me.approximations.security.services;

import jakarta.validation.Valid;
import me.approximations.dtos.UserLoginDTO;
import me.approximations.dtos.UserRegisterDTO;
import me.approximations.entities.User;
import me.approximations.security.entities.SecurityUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface CustomUserDetailsService extends UserDetailsService {
    SecurityUserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

    User registerUser(UserRegisterDTO dto);

    User registerUserOauth(OidcUser oidcUser);

    User createUser(UserRegisterDTO dto);

    User createUserOauth(OidcUser oidcUser);

    String login(@Valid UserLoginDTO dto);
}
