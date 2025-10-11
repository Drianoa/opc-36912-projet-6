package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.features.auth.UserRepository;
import com.openclassrooms.mddapi.features.topic.dto.UserSubscribedTopicDto;
import com.openclassrooms.mddapi.features.topic.dto.UserTopicDto;
import com.openclassrooms.mddapi.model.Topic;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TopicService implements ITopicService {

    private final UserRepository userRepository;
    private TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    public List<UserSubscribedTopicDto> getTopicsWithSubscriptionStatus() {
        // current user id should be passed here
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return topicRepository.findTopicsBySubscriptionStatus(email);
    }

    @Override
    public List<UserTopicDto> getSubscribedTopics() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var currentUser = userRepository.findUserByEmail(email);
        var userSet = currentUser.stream().collect(Collectors.toSet());
        return topicRepository.findTopicsByUsers(userSet);
    }
}
