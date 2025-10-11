package com.openclassrooms.mddapi.features.comment;

public interface CommentResponseDto {
    Integer getId();

    String getMessage();

    Author getUser();

    interface Author {
        String getUsername();
    }
}
