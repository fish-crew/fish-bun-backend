package fish.common.community.post.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private String firstOption;
    private String secondOption;
    private List<MultipartFile> pictures;
}
