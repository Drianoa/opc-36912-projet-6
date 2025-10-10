package com.openclassrooms.mddapi.features.comment;

public interface CommentResponseDto {
    Long getId();

    String getMessage();

    Author getUser();

    interface Author {
        String getUsername();
    }
}
