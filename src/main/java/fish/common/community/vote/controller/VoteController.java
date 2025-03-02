package fish.common.community.vote.controller;

import fish.common.community.vote.dto.request.VoteRequest;
import fish.common.community.vote.service.VoteService;
import fish.common.user.entity.User;
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
        List<Map<String, Object>> voteCount = voteService.saveVote(postId, user.getId(), request);
        template.convertAndSend("/topic/vote/" + postId, voteCount);
    }
}
