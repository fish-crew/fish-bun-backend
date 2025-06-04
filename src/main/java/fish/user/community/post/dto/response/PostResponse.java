package fish.user.community.post.dto.response;

import fish.user.community.post.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private int voteCount;
    private LocalDateTime regDate;

    public static PostResponse toResponse(PostEntity post, int voteCount) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .voteCount(voteCount)
                .regDate(post.getRegDate())
                .build();
    }
}
