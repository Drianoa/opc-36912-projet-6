package com.openclassrooms.mddapi.features.post;

import com.openclassrooms.mddapi.features.auth.UserRepository;
import com.openclassrooms.mddapi.features.topic.TopicRepository;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public Iterable<PostResponseDto> getPostsForCurrentUser() {
        // current user id should be passed here
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return postRepository.findByPostsForUser(email);
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
