package com.openclassrooms.mddapi.features.comment;

import com.openclassrooms.mddapi.dtos.MessageResponseDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Tag(name = "Comments", description = "Post management and retrieval")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{postId}")
    public List<CommentResponseDto> getPostComments(@PathVariable Integer postId) {
        return commentService.getCommentsForPost(postId);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<MessageResponseDto> addComment(
            @Valid @RequestBody CommentRequestDto commentRequestDto, @PathVariable Integer postId) {
        commentService.addComment(postId, commentRequestDto);
        return ResponseEntity.ok(new MessageResponseDto("Comment added successfully!"));
    }
}
