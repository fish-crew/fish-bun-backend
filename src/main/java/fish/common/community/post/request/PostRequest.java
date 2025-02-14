package fish.common.community.post.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private String firstOption;
    private String secondOption;
}
