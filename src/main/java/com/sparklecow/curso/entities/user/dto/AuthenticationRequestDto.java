package com.sparklecow.curso.entities.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthenticationRequestDto(
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        @NotNull(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @NotNull(message = "Password is required")
        @Size(min=8, message = "Password must be at least 8 characters long")
        String password
) {
}
