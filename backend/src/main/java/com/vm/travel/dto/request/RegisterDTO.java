package com.vm.travel.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String name,
        @Size(min = 3)
        @NotBlank
        String password
) {
}
