package com.openclassrooms.mddapi.features.auth.dto;

/**
 * DTO for login response.
 * <p>
 * This class is used to return the JWT token generated for the user.
 * </p>
 *
 * @param token the JWT token generated for the user
 */
public record LoginResponseDto(String token) {
}
