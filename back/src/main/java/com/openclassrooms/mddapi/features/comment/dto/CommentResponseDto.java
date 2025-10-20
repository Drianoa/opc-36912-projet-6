package com.openclassrooms.mddapi.features.comment.dto;

public interface CommentResponseDto {
    Integer getId();

    String getMessage();

    Author getUser();

    interface Author {
        String getUsername();
    }
}
