package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.features.topic.dto.UserSubscribedTopicDto;
import com.openclassrooms.mddapi.features.topic.dto.UserTopicDto;
import com.openclassrooms.mddapi.model.Topic;
import java.util.List;

public interface ITopicService {

    List<Topic> getTopics();

    List<UserSubscribedTopicDto> getTopicsWithSubscriptionStatus();

    List<UserTopicDto> getSubscribedTopics();
}
