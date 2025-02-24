package fish.common.community.comment.dto.response;

import fish.common.community.comment.entity.CommentEntity;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String contents;
    private int likeCount;
    private String userNickName;
    private LocalDateTime regDate;

    public static CommentResponse toResponse(CommentEntity entity) {
        return CommentResponse.builder()
                .id(entity.getId())
                .contents(entity.getContents())
                .likeCount(entity.getLikeCount())
                .userNickName(entity.getUserNickName())
                .regDate(entity.getRegDate())
                .build();
    }
}
