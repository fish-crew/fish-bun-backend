package fish.user.community.vote.dto.request;

import lombok.Data;

@Data
public class VoteRequest {
    private String voteOption;
    private Long userId;
}