package fish.common.community.post.response;

import fish.common.community.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
public class PostDetailResponse {
    private Long id;
    private String title;
    private String content;
    private String firstOption;
    private String secondOption;
    private Long firstOptionCount;
    private Long secondOptionCount;
    private List<String> fileUrls;
    private LocalDateTime regDate;

    @Builder
    public PostDetailResponse(Long id, String title, String content, String firstOption, String secondOption, Long firstOptionCount, Long secondOptionCount, List<String> fileUrls, LocalDateTime regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.firstOptionCount = firstOptionCount;
        this.secondOptionCount = secondOptionCount;
        this.fileUrls = fileUrls;
        this.regDate = regDate;
    }

    public static PostDetailResponse toResponse(Post post, List<String> fileUrls) {
        return PostDetailResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .firstOption(Optional.ofNullable(post.getFirstOption()).orElse(""))
                .secondOption(Optional.ofNullable(post.getSecondOption()).orElse(""))
                .firstOptionCount(Optional.ofNullable(post.getFirstOptionCount()).orElse(0L))
                .secondOptionCount(Optional.ofNullable(post.getSecondOptionCount()).orElse(0L))
                .fileUrls(fileUrls)
                .regDate(post.getRegDate())
                .build();
    }
}
