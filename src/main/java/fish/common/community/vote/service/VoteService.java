package fish.common.community.vote.service;

import fish.common.community.vote.dto.request.VoteRequest;
import fish.common.community.vote.entity.VoteEntity;
import fish.common.community.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    @Transactional
    public List<Map<String, Object>> saveVote(Long postId, Long userId, VoteRequest request) {
        Optional<VoteEntity> vote = voteRepository.findByPostIdAndUserId(postId, userId);
        // 중복 투표의 경우 기존 투표 취소
        if (vote.isPresent()) {
            voteRepository.delete(vote.get());
        }
        else {
            voteRepository.save(VoteEntity.toEntity(postId, userId, request));
        }

        // 현재 집계 수 소켓 메세지로 보내주기
        return voteRepository.findVoteCountsByPostId(postId);
    }
}
