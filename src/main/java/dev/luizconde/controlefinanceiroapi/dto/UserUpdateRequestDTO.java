package dev.luizconde.controlefinanceiroapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO(
        String name,
        @Email
        String email,
        @Size(min = 6, message = "Password must be 6 characters")
        String password
) {
}
