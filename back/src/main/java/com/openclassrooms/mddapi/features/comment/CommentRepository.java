package com.openclassrooms.mddapi.features.comment;

import com.openclassrooms.mddapi.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<CommentResponseDto> findByPost_IdOrderByCreatedAtAsc(Integer postId);
}
