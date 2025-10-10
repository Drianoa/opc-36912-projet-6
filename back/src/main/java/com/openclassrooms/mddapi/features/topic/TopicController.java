package com.openclassrooms.mddapi.features.topic;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearerAuth")
public class TopicController {

    private final ITopicService topicService;

    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<UserSubscribedTopicDto> getTopics() {
        return topicService.getTopicsWithSubscriptionStatus();
    }

    @GetMapping("/subscribed")
    public List<UserTopicDto> getSubscribedTopics() {
        return topicService.getSubscribedTopics();
    }
}
