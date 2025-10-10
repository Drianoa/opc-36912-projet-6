package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.model.Topic;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final ITopicService topicService;

    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<Topic> getTopics() {
        return topicService.getTopics();
    }

    @GetMapping("/user")
    public List<UserTopicDto> getTopicsWithSubscriptionStatus() {
        return topicService.getTopicsWithSubscriptionStatus();
    }
}
