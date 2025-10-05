package com.openclassrooms.mddapi.features.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for user registration requests.
 * Contains the necessary information to register a new user in the system.
 *
 * @param email    The email address of the user. Must be between 3 and 255 characters long.
 * @param name     The full name of the user. Must be between 3 and 255 characters long.
 * @param password The password for the user account. Must be at least 3 characters long.
 */
public record RegisterDto(
        @NotBlank(message = "Email is required")
        @Size(min = 3, max = 255, message = "Email must be between {min} and {max} characters")
        String email,

        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 255, message = "Name must be between {min} and {max} characters")
        String name,

        @NotBlank(message = "Password is required")
        @Size(min = 3, message = "Password must be at least {min} characters long")
        String password
) {
}
