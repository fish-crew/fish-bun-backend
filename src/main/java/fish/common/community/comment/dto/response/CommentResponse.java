package fish.common.community.comment.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String contents;
    private int likeCount;
    private String userNickname;
    private char likeYN;
    private String regDate;

    public static CommentResponse toResponse(Map<String, Object> data) {
        return CommentResponse.builder()
                .id(Long.parseLong(data.get("id").toString()))
                .contents(data.get("contents").toString())
                .likeCount(Integer.parseInt(data.get("likeCount").toString()))
                .userNickname(data.get("userNickname").toString())
                .regDate(data.get("regDate").toString())
                .build();
    }
}