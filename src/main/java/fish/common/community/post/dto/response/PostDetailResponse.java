package fish.common.community.post.dto.response;

import fish.common.community.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
public class PostDetailResponse {
    private Long id;
    private String title;
    private String contents;
    private String firstOption;
    private String secondOption;
    private List<String> fileUrls;
    private LocalDateTime regDate;

    @Builder
    public PostDetailResponse(Long id, String title, String contents, String firstOption, String secondOption, List<String> fileUrls, LocalDateTime regDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.fileUrls = fileUrls;
        this.regDate = regDate;
    }

    public static PostDetailResponse toResponse(PostEntity post, List<String> fileUrls) {
        return PostDetailResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .firstOption(Optional.ofNullable(post.getFirstOption()).orElse(""))
                .secondOption(Optional.ofNullable(post.getSecondOption()).orElse(""))
                .fileUrls(fileUrls)
                .regDate(post.getRegDate())
                .build();
    }
}
