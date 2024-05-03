package me.approximations.security.services;

import jakarta.validation.Valid;
import me.approximations.dtos.UserLoginDTO;
import me.approximations.dtos.UserOauthRegisterDTO;
import me.approximations.dtos.UserRegisterDTO;
import me.approximations.entities.User;
import me.approximations.security.entities.SecurityUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {
    SecurityUserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

    User registerUser(UserRegisterDTO dto);

    User registerUserOauth(UserOauthRegisterDTO dto);

    User createUser(UserRegisterDTO dto);

    User createUserOauth(UserOauthRegisterDTO dto);

    String login(@Valid UserLoginDTO dto);
}
