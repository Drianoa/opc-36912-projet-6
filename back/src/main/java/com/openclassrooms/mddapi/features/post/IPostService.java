package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.model.Post;

public interface IPostService {
    Iterable<PostResponseDto> getPostsForCurrentUser();

    Post createPost(PostRequestDto postRequest);
}
