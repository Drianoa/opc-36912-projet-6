package com.openclassrooms.mddapi.features.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostRequestDto(
        @NotBlank(message = "Le titre est obligatoire")
        @Size(max = 255, message = "Le titre ne peut pas dépasser 255 caractères")
        String title,

        @NotBlank(message = "Le contenu est obligatoire")
        @Size(max = 2000, message = "Le contenu ne peut pas dépasser 2000 caractères")
        String content,

        @NotNull(message = "Le thème est obligatoire") Integer topicId) {}
