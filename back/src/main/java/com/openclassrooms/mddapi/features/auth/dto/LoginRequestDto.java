package com.openclassrooms.mddapi.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for email requests.
 * Contains the user's credentials for authentication.
 *
 * @param login The user's email address (must not be blank)
 * @param password The user's password (must not be blank)
 */
public record LoginRequestDto(@NotBlank String login, @NotBlank String password) {}
