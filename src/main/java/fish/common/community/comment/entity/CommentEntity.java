package fish.common.community.comment.entity;

import fish.common.community.comment.dto.request.CommentRequest;
import fish.common.user.entity.User;
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
@Table(name = "COMMENT")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String contents;
    private int likeCount;
    @NotNull
    private Long postId;
    @NotNull
    private Long userId;
    @NotNull
    private String userNickName;
    @CreationTimestamp
    private LocalDateTime regDate;

    public static CommentEntity toEntity(Long postId, CommentRequest request, User user) {
        return CommentEntity.builder()
                .contents(request.getContents())
                .likeCount(0)
                .postId(postId)
                .userId(user.getId())
                .userNickName(user.getNickname())
                .build();
    }

    public void modifyContents(String contents) {
        this.contents = contents;
    }
}
