package com.openclassrooms.mddapi.features.topic;

import com.openclassrooms.mddapi.features.topic.dto.UserSubscribedTopicDto;
import com.openclassrooms.mddapi.features.topic.dto.UserTopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    @Query(
            """
        SELECT
            t.id AS id,
            t.name AS name,
            t.description AS description,
            CASE WHEN (COUNT(u.id) > 0) THEN true ELSE false END AS subscribed,
            COUNT(u.id) AS userCount
        FROM Topic t
        LEFT JOIN t.users u
        WITH u.email = :email
        GROUP BY t.id, t.name, t.description
        ORDER BY userCount ASC , t.name ASC
        """)
    List<UserSubscribedTopicDto> findTopicsBySubscriptionStatus(String email);

    List<UserTopicDto> findTopicsByUsers(Set<User> users);
}
