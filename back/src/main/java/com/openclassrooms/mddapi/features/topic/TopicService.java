package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.model.Topic;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TopicService implements ITopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    public List<UserTopicDto> getTopicsWithSubscriptionStatus() {
        // current user id should be passed here
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return topicRepository.findTopicsBySubscriptionStatus(email);
    }
}
