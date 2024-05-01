package me.approximations.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserOauthRegisterDTO(@NotBlank String name, @NotBlank @Email String email) {
}
