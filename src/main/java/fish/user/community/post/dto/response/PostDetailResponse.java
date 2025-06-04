package fish.user.community.post.dto.response;

import fish.user.community.post.entity.PostEntity;
import fish.global.util.StringUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
public class PostDetailResponse {
    private Long id;
    private String title;
    private String contents;
    private String firstOption;
    private String secondOption;
    private int firstOptionCount;
    private int secondOptionCount;
    private String selectedOption;
    private List<String> fileUrls;
    private String regDate;

    @Builder
    public PostDetailResponse(Long id, String title, String contents,
                              String firstOption, String secondOption,
                              List<String> fileUrls, String regDate,
                              int firstOptionCount, int secondOptionCount, String selectedOption) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.fileUrls = fileUrls;
        this.regDate = regDate;
        this.firstOptionCount = firstOptionCount;
        this.secondOptionCount = secondOptionCount;
        this.selectedOption = selectedOption;
    }

    /**
     * User Response
     * */
    public static PostDetailResponse toResponse(Map<String, Object> post, List<String> fileUrls) {
        return PostDetailResponse.builder()
                .id(Long.parseLong(post.get("id").toString()))
                .title(StringUtil.NVL(post.get("title")))
                .contents(StringUtil.NVL(post.get("contents")))
                .firstOption(post.get("firstOption").toString())
                .secondOption(post.get("secondOption").toString())
                .fileUrls(fileUrls)
                .regDate(StringUtil.NVL(post.get("regDate")))
                .firstOptionCount(Integer.parseInt(post.get("firstOptionCount").toString()))
                .secondOptionCount(Integer.parseInt(post.get("secondOptionCount").toString()))
                .selectedOption(StringUtil.NVL(post.get("selectedOption")))
                .build()
                ;
    }

    /**
     * Admin Response
     * */
    public static PostDetailResponse toResponse(PostEntity post, List<String> fileUrls) {
        return PostDetailResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .firstOption(Optional.ofNullable(post.getFirstOption()).orElse(""))
                .secondOption(Optional.ofNullable(post.getSecondOption()).orElse(""))
                .fileUrls(fileUrls)
                .regDate(post.getRegDate().toString())
                .build()
                ;
    }
}
