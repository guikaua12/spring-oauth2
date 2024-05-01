package me.approximations.security.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.approximations.dtos.UserLoginDTO;
import me.approximations.dtos.UserRegisterDTO;
import me.approximations.entities.User;
import me.approximations.exceptions.UserAlreadyExistsException;
import me.approximations.repositories.UserRepository;
import me.approximations.security.entities.SecurityUserDetails;
import me.approximations.security.services.CustomUserDetailsService;
import me.approximations.services.jwt.JwtTokenService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return loadUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        final User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email or password not found."));

        return new SecurityUserDetails(user);
    }

    @Override
    public User createUser(@Valid UserRegisterDTO dto) {
        final Optional<User> userByEmail = userRepository.findUserByEmail(dto.email());
        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        final User user = new User(null, dto.name(), dto.email(), passwordEncoder.encode(dto.password()));
        return userRepository.save(user);
    }

    @Override
    public String login(@Valid UserLoginDTO dto) {
        final UserDetails userDetails = this.loadUserByUsername(dto.email());

        if (!passwordEncoder.matches(dto.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Email or password not found.");
        }

        return jwtTokenService.generateToken(userDetails);
    }
}
