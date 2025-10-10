package com.openclassrooms.mddapi.features.comment;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDto(@NotBlank String message) {}
