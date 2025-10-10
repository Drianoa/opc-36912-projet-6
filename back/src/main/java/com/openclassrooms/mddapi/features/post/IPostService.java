package com.openclassrooms.mddapi.features.post;

public interface IPostService {
    Iterable<PostResponseDto> getPostsForCurrentUser();
}
