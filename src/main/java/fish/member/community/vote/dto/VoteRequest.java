package fish.member.community.vote.dto;

import lombok.Data;

@Data
public class VoteRequest {
    private String voteOption;
    private Long userId;
}