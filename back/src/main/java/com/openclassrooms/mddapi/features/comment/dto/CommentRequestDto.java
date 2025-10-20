package com.openclassrooms.mddapi.features.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDto(@NotBlank String message) {}
