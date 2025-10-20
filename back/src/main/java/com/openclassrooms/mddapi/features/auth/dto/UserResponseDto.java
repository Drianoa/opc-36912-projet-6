package com.openclassrooms.mddapi.features.auth.dto;

/**
 * Data Transfer Object for user response.
 * Contains user information to be sent as a response to API requests.
 *
 * @param id The unique identifier of the user
 * @param username The full username of the user
 * @param email The email address of the user
 */
public record UserResponseDto(Integer id, String username, String email) {}
