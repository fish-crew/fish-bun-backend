package fish.member.community.vote.controller;

import fish.member.community.vote.dto.VoteRequest;
import fish.member.community.vote.service.VoteService;
import fish.domain.user.index.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/vote/{postId}")
    public void saveVote(@DestinationVariable Long postId, @AuthenticationPrincipal User user, VoteRequest request) {
        List<Map<String, Object>> voteCount = voteService.saveVote(postId, request);
        template.convertAndSend("/topic/vote/" + postId, voteCount);
    }
}
