package com.openclassrooms.mddapi.features.comment;

import com.openclassrooms.mddapi.features.auth.User;
import com.openclassrooms.mddapi.features.post.Post;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @CreatedBy
    private User user;

    @Column(name = "message", nullable = false, length = 2000)
    private String message;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;
}
