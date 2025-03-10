package fish.common.community.comment.index.entity;

import fish.common.community.comment.index.dto.request.CommentRequest;
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
    @NotNull
    private Long postId;
    @NotNull
    private Long userId;
    @NotNull
    private String userNickname;
    @CreationTimestamp
    private LocalDateTime regDate;

    public static CommentEntity toEntity(Long postId, CommentRequest request, User user) {
        return CommentEntity.builder()
                .contents(request.getContents())
                .postId(postId)
                .userId(user.getId())
                .userNickname(user.getNickname())
                .build();
    }

    public void modifyContents(String contents) {
        this.contents = contents;
    }
}
