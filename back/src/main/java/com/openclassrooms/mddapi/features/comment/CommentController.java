package com.openclassrooms.mddapi.features.comment;

import com.openclassrooms.mddapi.dtos.MessageResponseDto;
import com.openclassrooms.mddapi.features.comment.dto.CommentRequestDto;
import com.openclassrooms.mddapi.features.comment.dto.CommentResponseDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing comments on posts.
 * Provides endpoints for retrieving and adding comments to specific posts.
 */
@Log4j2
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Tag(name = "Comments", description = "Post management and retrieval")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    private final CommentService commentService;

    /**
     * Retrieves all comments for a specific post.
     *
     * @param postId the ID of the post to retrieve comments for
     * @return a list of comments associated with the specified post
     */
    @GetMapping("/{postId}")
    public List<CommentResponseDto> getPostComments(@PathVariable Integer postId) {
        return commentService.getCommentsForPost(postId);
    }

    /**
     * Adds a new comment to a specific post.
     *
     * @param commentRequestDto the comment data to be added (validated request body)
     * @param postId the ID of the post to add the comment to
     * @return a response entity with a success message
     */
    @PostMapping("/{postId}")
    public ResponseEntity<MessageResponseDto> addComment(
            @Valid @RequestBody CommentRequestDto commentRequestDto, @PathVariable Integer postId) {
        commentService.addComment(postId, commentRequestDto);
        return ResponseEntity.ok(new MessageResponseDto("Comment added successfully!"));
    }
}
