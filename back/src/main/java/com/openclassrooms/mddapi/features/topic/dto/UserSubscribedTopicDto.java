package com.openclassrooms.mddapi.features.topic.dto;

public interface UserSubscribedTopicDto extends UserTopicDto {
    boolean isSubscribed();
}
