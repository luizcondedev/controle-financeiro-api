package dev.luizconde.controlefinanceiroapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserRequestDTO(
        @NotBlank(message = "Name field is required.")
        String name,
        @Email
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be 6 characters")
        String password
) {
}
