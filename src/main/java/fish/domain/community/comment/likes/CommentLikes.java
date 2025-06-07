package fish.domain.community.comment.likes;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "COMMENT_LIKES")
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long commentId;
    @NotNull
    private Long userId;
    @CreationTimestamp
    private LocalDateTime regDate;

    public static CommentLikes toEntity(Long commentId, Long userId) {
        return CommentLikes.builder()
                .commentId(commentId)
                .userId(userId)
                .build();
    }
}
