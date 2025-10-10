package com.openclassrooms.mddapi.features.comment;

import com.openclassrooms.mddapi.features.auth.UserRepository;
import com.openclassrooms.mddapi.features.post.PostRepository;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@SecurityRequirement(name = "bearerAuth")
public class CommentService {
    public final CommentRepository commentRepository;
    public final PostRepository postRepository;
    public final UserRepository userRepository;

    public List<CommentResponseDto> getCommentsForPost(Integer postId) {
        return commentRepository.findByPost_IdOrderByCreatedAtAsc(postId);
    }

    public Comment addComment(Integer postId, @Valid CommentRequestDto commentRequestDto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var currentUser = userRepository.findUserByEmail(email);
        User user = currentUser.orElseThrow(() -> new RuntimeException("Error: User not found."));

        var newComment = Comment.builder()
                .post(postRepository.getReferenceById(postId))
                .message(commentRequestDto.message())
                .user(userRepository.getReferenceById(user.getId()))
                .build();

        return commentRepository.save(newComment);
    }
}
