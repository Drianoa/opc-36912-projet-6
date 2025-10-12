package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.features.topic.dto.TopicSubscriptionRequestDto;
import com.openclassrooms.mddapi.features.topic.dto.UserSubscribedTopicDto;
import com.openclassrooms.mddapi.features.topic.dto.UserTopicDto;
import com.openclassrooms.mddapi.model.Topic;
import java.util.List;

public interface ITopicService {

    List<Topic> getTopics();

    List<UserSubscribedTopicDto> getTopicsWithSubscriptionStatus();

    List<UserTopicDto> getSubscribedTopics();

    /**
     * Subscribe current user to a topic.
     *
     * @param request DTO containing the topic ID to subscribe to
     * @throws RuntimeException if topic not found or user already subscribed
     */
    void subscribeToTopic(TopicSubscriptionRequestDto request);

    /**
     * Unsubscribe current user from a topic.
     *
     * @param request DTO containing the topic ID to unsubscribe from
     * @throws RuntimeException if topic not found or user not subscribed
     */
    void unsubscribeFromTopic(TopicSubscriptionRequestDto request);
}
