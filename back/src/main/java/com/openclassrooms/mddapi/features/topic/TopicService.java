package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.features.auth.User;
import com.openclassrooms.mddapi.features.auth.UserRepository;
import com.openclassrooms.mddapi.features.topic.dto.TopicSubscriptionRequestDto;
import com.openclassrooms.mddapi.features.topic.dto.UserSubscribedTopicDto;
import com.openclassrooms.mddapi.features.topic.dto.UserTopicDto;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService implements ITopicService {

    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    @Override
    public List<UserSubscribedTopicDto> getTopicsWithSubscriptionStatus() {
        User currentUser =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return topicRepository.findTopicsBySubscriptionStatus(currentUser);
    }

    @Override
    public List<UserTopicDto> getSubscribedTopics() {
        User currentUser =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userSet = Set.of(currentUser);
        return topicRepository.findTopicsByUsers(userSet);
    }

    @Transactional
    public Topic getTopicById(Integer topicId) {
        if (topicRepository.existsById(topicId)) {
            return topicRepository.getReferenceById(topicId);
        }
        throw new RuntimeException("Topic not found");
    }

    @Override
    @Transactional
    public void subscribeToTopic(TopicSubscriptionRequestDto request) {
        User user =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userRepository.getReferenceById(user.getId());
        Topic topic = getTopicById(request.topicId());

        // Subscribe user to topic
        currentUser.getTopics().add(topic);

        // Save both entities
        userRepository.save(currentUser);
    }

    @Override
    @Transactional
    public void unsubscribeFromTopic(TopicSubscriptionRequestDto request) {
        User user =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userRepository.getReferenceById(user.getId());
        Topic topic = getTopicById(request.topicId());

        // Unsubscribe user from topic
        currentUser.getTopics().remove(topic);

        // Save both entities
        userRepository.save(currentUser);
    }
}
