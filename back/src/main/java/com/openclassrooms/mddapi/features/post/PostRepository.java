package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.features.post.dto.PostResponseDto;
import com.openclassrooms.mddapi.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(
            """
        SELECT p FROM Post p
        JOIN FETCH p.topic t
        JOIN FETCH t.users u
        WHERE u.email = :email
        ORDER BY p.createdAt DESC
        """)
    List<PostResponseDto> findByPostsForUser(String email);
}
