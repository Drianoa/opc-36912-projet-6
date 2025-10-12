package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.features.topic.dto.TopicSubscriptionRequestDto;
import com.openclassrooms.mddapi.features.topic.dto.UserSubscribedTopicDto;
import com.openclassrooms.mddapi.features.topic.dto.UserTopicDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing topics.
 * Provides endpoints for retrieving topics, subscription status, and managing topic subscriptions for the current user.
 */
@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Topics", description = "Topic management, retrieval and subscription operations")
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
    @Operation(
            summary = "Get all topics with subscription status",
            description = "Retrieves all available topics along with subscription status for the current user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved topics")
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

    /**
     * Subscribe current user to a topic.
     *
     * @param request DTO containing the topic ID to subscribe to
     * @return ResponseEntity with success message
     */
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToTopic(@Valid @RequestBody TopicSubscriptionRequestDto request) {
        try {
            topicService.subscribeToTopic(request);
            return ResponseEntity.ok("Successfully subscribed to topic");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Unsubscribe current user from a topic.
     *
     * @param request DTO containing the topic ID to unsubscribe from
     * @return ResponseEntity with success message
     */
    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribeFromTopic(@Valid @RequestBody TopicSubscriptionRequestDto request) {
        try {
            topicService.unsubscribeFromTopic(request);
            return ResponseEntity.ok("Successfully unsubscribed from topic");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
