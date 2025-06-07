package fish.domain.community.comment.index;

import fish.member.community.comment.index.dto.request.CommentRequest;
import fish.domain.user.index.User;
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
public class Comment {
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

    public static Comment toEntity(Long postId, CommentRequest request, User user) {
        return Comment.builder()
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
