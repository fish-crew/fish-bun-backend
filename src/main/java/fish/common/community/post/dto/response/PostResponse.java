package fish.common.community.post.dto.response;

import fish.common.community.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private LocalDateTime regDate;

    @Builder
    public PostResponse(Long id, String title, LocalDateTime regDate) {
        this.id = id;
        this.title = title;
        this.regDate = regDate;
    }

    public static PostResponse toResponse(PostEntity post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .regDate(post.getRegDate())
                .build();
    }
}
