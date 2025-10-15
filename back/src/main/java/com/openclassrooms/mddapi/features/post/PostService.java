package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.features.post.dto.PostRequestDto;
import com.openclassrooms.mddapi.features.post.dto.PostResponseDto;
import com.openclassrooms.mddapi.features.post.dto.SortDirection;
import com.openclassrooms.mddapi.features.topic.TopicRepository;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final TopicRepository topicRepository;

    @Override
    public Iterable<PostResponseDto> getPostsForCurrentUser(SortDirection direction) {
        User user =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Sort sort = direction == SortDirection.NEWEST
                ? Sort.by(Sort.Direction.DESC, "createdAt")
                : Sort.by(Sort.Direction.ASC, "createdAt");

        return postRepository.findByPostsForUser(user, sort);
    }

    @Override
    @Transactional
    public Post createPost(PostRequestDto postRequest) {

        // Récupérer le thème
        Topic topic = topicRepository.getReferenceById(postRequest.topicId());

        // Créer le post
        var post = Post.builder()
                .title(postRequest.title())
                .content(postRequest.content())
                .topic(topic)
                .build();

        return postRepository.save(post);
    }

    @Override
    public PostResponseDto getPost(String postId) {
        return postRepository.findDtoById(Integer.valueOf(postId));
    }
}
