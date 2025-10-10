package com.openclassrooms.mddapi.features.post;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Post management and retrieval")
public class PostController {
    private final IPostService postService;

    @GetMapping
    Iterable<PostResponseDto> getPostsForCurrentUser() {
        return postService.getPostsForCurrentUser();
    }
}
