package fish.user.community.comment.likes.entity;


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
public class CommentLikesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long commentId;
    @NotNull
    private Long userId;
    @CreationTimestamp
    private LocalDateTime regDate;

    public static CommentLikesEntity toEntity(Long commentId, Long userId) {
        return CommentLikesEntity.builder()
                .commentId(commentId)
                .userId(userId)
                .build();
    }
}
