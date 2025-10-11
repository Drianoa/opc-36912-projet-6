package com.openclassrooms.mddapi.features.post.dto;

import java.time.Instant;

public interface PostResponseDto {
    Integer getId();

    String getTitle();

    String getContent();

    Instant getCreatedAt();

    TopicName getTopic();

    OwnerUsername getOwner();

    interface TopicName {

        String getName();
    }

    interface OwnerUsername {

        String getUsername();
    }
}
