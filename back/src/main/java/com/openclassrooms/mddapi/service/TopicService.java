package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import java.util.List;
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
}
