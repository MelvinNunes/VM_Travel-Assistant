package com.vm.travel.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String name,
        @NotBlank
        String password
) {
}
