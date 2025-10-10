package com.openclassrooms.mddapi.features.comment;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
