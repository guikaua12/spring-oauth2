package me.approximations.services.user;

import jakarta.validation.Valid;
import me.approximations.dtos.UserLoginDTO;
import me.approximations.dtos.UserRegisterDTO;
import me.approximations.entities.User;

public interface UserService {
    User createUser(UserRegisterDTO dto);

    String login(@Valid UserLoginDTO dto);
}
