package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.features.auth.User;
import com.openclassrooms.mddapi.features.post.dto.PostResponseDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("""
        SELECT p FROM Post p
        JOIN FETCH p.topic t
        JOIN FETCH t.users u
        WHERE u = :user
        """)
    List<PostResponseDto> findByPostsForUser(User user, Sort sort);

    @Query("""
        SELECT p FROM Post p
        JOIN FETCH p.topic t
        JOIN FETCH p.owner o
        WHERE p.id = :id
        """)
    Optional<PostResponseDto> findDtoById(Integer id);
}
