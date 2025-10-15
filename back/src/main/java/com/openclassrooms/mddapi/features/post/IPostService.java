package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.features.post.dto.PostRequestDto;
import com.openclassrooms.mddapi.features.post.dto.PostResponseDto;
import com.openclassrooms.mddapi.features.post.dto.SortDirection;
import com.openclassrooms.mddapi.model.Post;

public interface IPostService {
    Iterable<PostResponseDto> getPostsForCurrentUser(SortDirection sortDirection);

    Post createPost(PostRequestDto postRequest);

    PostResponseDto getPost(String postId);
}
