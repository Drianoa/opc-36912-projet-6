package com.openclassrooms.mddapi.features.topic.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO for topic subscription/unsubscription requests.
 * Contains the topic ID to subscribe to or unsubscribe from.
 */
public record TopicSubscriptionRequestDto(
        @NotNull(message = "Topic ID is required") Integer topicId) {}
