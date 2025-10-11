package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.features.auth.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public Iterable<PostResponseDto> getPostsForCurrentUser(SortDirection direction) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Sort sort = direction == SortDirection.NEWEST
                ? Sort.by(Sort.Direction.DESC, "createdAt")
                : Sort.by(Sort.Direction.ASC, "createdAt");

        return postRepository.findByPostsForUser(email, sort);
    }

    @Override
    @Transactional
    public Post createPost(PostRequestDto postRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Récupérer l'utilisateur actuel
        User currentUser =
                userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Récupérer le thème
        Topic topic = topicRepository
                .findById(postRequest.topicId())
                .orElseThrow(() -> new RuntimeException("Thème non trouvé"));

        // Créer le post
        var post = Post.builder()
                .title(postRequest.title())
                .content(postRequest.content())
                .topic(topic)
                .owner(currentUser)
                .build();

        return postRepository.save(post);
    }
}
