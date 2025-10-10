package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.dtos.MessageResponseDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Post management and retrieval")
@SecurityRequirement(name = "bearerAuth")
public class PostController {
    private final IPostService postService;

    @GetMapping
    public Iterable<PostResponseDto> getPostsForCurrentUser() {
        return postService.getPostsForCurrentUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createPost(@Valid @RequestBody PostRequestDto postRequest) {
        postService.createPost(postRequest);
        return new MessageResponseDto("Post created");
    }
}
