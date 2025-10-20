package com.openclassrooms.mddapi.features.comment;

import com.openclassrooms.mddapi.features.comment.dto.CommentRequestDto;
import com.openclassrooms.mddapi.features.comment.dto.CommentResponseDto;
import com.openclassrooms.mddapi.features.post.PostRepository;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<CommentResponseDto> getCommentsForPost(Integer postId) {
        return commentRepository.findByPost_IdOrderByCreatedAtAsc(postId);
    }

    @Transactional
    public Comment addComment(Integer postId, @Valid CommentRequestDto commentRequestDto) {
        // Récupérer l'ID utilisateur directement du token JWT
        var newComment = Comment.builder()
                .post(postRepository.getReferenceById(postId))
                .message(commentRequestDto.message())
                .build();

        return commentRepository.save(newComment);
    }
}
