package com.example.exapp.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "O nome de usuario/username não pode ser nulo")
        String username,
        @NotBlank(message = "A senha não pode ser nula")
        String password
) {
}
