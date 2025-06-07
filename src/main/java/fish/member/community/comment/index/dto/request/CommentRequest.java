package fish.member.community.comment.index.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentRequest {
    @NotNull
    private String contents;
}
