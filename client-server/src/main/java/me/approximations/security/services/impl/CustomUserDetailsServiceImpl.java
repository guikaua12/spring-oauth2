package me.approximations.security.services.impl;

import lombok.RequiredArgsConstructor;
import me.approximations.entities.User;
import me.approximations.repositories.UserRepository;
import me.approximations.security.entities.SecurityUserDetails;
import me.approximations.security.services.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return loadUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        final User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email or password not found."));

        return new SecurityUserDetails(user);
    }
}
