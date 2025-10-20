package com.openclassrooms.mddapi.dtos;

/**
 * Data Transfer Object (DTO) for representing error responses in the application.
 * This record is used to standardize the structure of error messages returned by the API.
 *
 * @param message a human-readable error message describing the issue that occurred
 */
public record ErrorResponseDto(String message) {}
