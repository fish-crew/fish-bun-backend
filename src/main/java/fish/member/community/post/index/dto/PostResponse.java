package fish.member.community.post.index.dto;

import fish.domain.community.post.index.Post;
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

    public static PostResponse toResponse(Post post, int voteCount) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .voteCount(voteCount)
                .regDate(post.getRegDate())
                .build();
    }
}
