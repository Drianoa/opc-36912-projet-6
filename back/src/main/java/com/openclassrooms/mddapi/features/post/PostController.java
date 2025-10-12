package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.dtos.MessageResponseDto;
import com.openclassrooms.mddapi.features.post.dto.PostRequestDto;
import com.openclassrooms.mddapi.features.post.dto.PostResponseDto;
import com.openclassrooms.mddapi.features.post.dto.SortDirection;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing posts.
 * Provides endpoints for retrieving and creating posts for the current user.
 */
@Log4j2
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Post management and retrieval")
@SecurityRequirement(name = "bearerAuth")
public class PostController {
    private final IPostService postService;

    /**
     * Retrieves posts for the current authenticated user with optional sorting.
     *
     * @param sort The sort direction for the posts (default: NEWEST)
     * @return An iterable of PostResponseDto containing the user's posts
     */
    @GetMapping
    public Iterable<PostResponseDto> getPostsForCurrentUser(@RequestParam(defaultValue = "NEWEST") SortDirection sort) {
        return postService.getPostsForCurrentUser(sort);
    }

    /**
     * Creates a new post for the current authenticated user.
     *
     * @param postRequest The post data to create (title and content are required)
     * @return A message response indicating the post was successfully created
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createPost(@Valid @RequestBody PostRequestDto postRequest) {
        postService.createPost(postRequest);
        return new MessageResponseDto("Post created");
    }
}
