package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.model.Topic;
import java.util.List;

public interface ITopicService {

    List<Topic> getTopics();

    List<UserTopicDto> getTopicsWithSubscriptionStatus();
}
