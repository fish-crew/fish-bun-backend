package fish.common.community.post.response;

import fish.common.community.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String firstOption;
    private String secondOption;
    private Long firstOptionCount;
    private Long secondOptionCount;
    private LocalDateTime regDate;

    @Builder
    public PostResponse(Long id, String title, String content, String firstOption, String secondOption, Long firstOptionCount, Long secondOptionCount, LocalDateTime regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.firstOptionCount = firstOptionCount;
        this.secondOptionCount = secondOptionCount;
        this.regDate = regDate;
    }

    public static PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .firstOption(Optional.ofNullable(post.getFirstOption()).orElse(""))
                .secondOption(Optional.ofNullable(post.getSecondOption()).orElse(""))
                .firstOptionCount(Optional.ofNullable(post.getFirstOptionCount()).orElse(0L))
                .secondOptionCount(Optional.ofNullable(post.getSecondOptionCount()).orElse(0L))
                .regDate(post.getRegDate())
                .build();
    }
}
