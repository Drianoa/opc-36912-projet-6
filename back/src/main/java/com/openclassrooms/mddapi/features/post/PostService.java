package com.openclassrooms.mddapi.features.post;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Iterable<PostResponseDto> getPostsForCurrentUser() {
        // current user id should be passed here
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return postRepository.findByPostsForUser(email);
    }
}
