package com.openclassrooms.mddapi.features.auth.dto;

import java.time.Instant;

/**
 * Data Transfer Object for user response.
 * Contains user information to be sent as a response to API requests.
 *
 * @param id The unique identifier of the user
 * @param name The full username of the user
 * @param email The email address of the user
 * @param createdAt The timestamp when the user was created
 * @param updatedAt The timestamp when the user was last updated
 */
public record UserResponseDto(Integer id, String name, String email, Instant createdAt, Instant updatedAt) {}
