package com.openclassrooms.mddapi.dtos;

/**
 * A simple data transfer object (DTO) that encapsulates a message response.
 * This is typically used to return simple string messages from API endpoints.
 *
 * @param message the message content to be included in the response
 */
public record MessageResponseDto(String message) {}
