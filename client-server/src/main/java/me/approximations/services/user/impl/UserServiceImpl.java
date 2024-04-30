package me.approximations.services.user.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.approximations.dtos.UserLoginDTO;
import me.approximations.dtos.UserRegisterDTO;
import me.approximations.entities.User;
import me.approximations.exceptions.UserAlreadyExistsException;
import me.approximations.repositories.UserRepository;
import me.approximations.services.jwt.JwtTokenService;
import me.approximations.services.user.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;

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
        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.email());

        if (!passwordEncoder.matches(dto.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Email or password not found.");
        }

        return jwtTokenService.generateToken(userDetails);
    }
}
