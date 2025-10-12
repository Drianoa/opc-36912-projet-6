package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.features.topic.dto.UserSubscribedTopicDto;
import com.openclassrooms.mddapi.features.topic.dto.UserTopicDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing topics.
 * Provides endpoints for retrieving topics and subscription status for the current user.
 */
@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Topics", description = "Topic management and retrieval")
public class TopicController {

    private final ITopicService topicService;

    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * Retrieves all available topics with their subscription status for the current user.
     *
     * @return A list of UserSubscribedTopicDto containing all topics and subscription information
     */
    @GetMapping
    public List<UserSubscribedTopicDto> getTopics() {
        return topicService.getTopicsWithSubscriptionStatus();
    }

    /**
     * Retrieves topics that the current user is subscribed to.
     *
     * @return A list of UserTopicDto containing the subscribed topics
     */
    @GetMapping("/subscribed")
    public List<UserTopicDto> getSubscribedTopics() {
        return topicService.getSubscribedTopics();
    }
}
