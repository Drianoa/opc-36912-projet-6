package com.openclassrooms.mddapi.features.comment;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@SecurityRequirement(name = "bearerAuth")
public class CommentService {
    public final CommentRepository commentRepository;

    public List<CommentResponseDto> getCommentsForPost(Integer postId) {
        return commentRepository.findByPost_IdOrderByCreatedAtAsc(postId);
    }

}
