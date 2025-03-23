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
    public List<Map<String, Object>> saveVote(Long postId, VoteRequest request) {
        Optional<VoteEntity> vote = voteRepository.findByPostIdAndUserId(postId, request.getUserId());

        // 1. 이미 동일한 투표를 했으면 취소 (삭제)
        if (vote.isPresent() && vote.get().getVoteOption().equals(request.getVoteOption())) {
            voteRepository.delete(vote.get());
            return voteRepository.findVoteCountsByPostId(postId);
        }

        // 2. 기존에 다른 선택지를 투표한 경우 기존 투표 삭제 후 새 투표 저장
        vote.ifPresent(voteRepository::delete);
        voteRepository.save(VoteEntity.toEntity(postId, request.getUserId(), request));

        // 현재 집계 수 소켓 메세지로 보내주기
        return voteRepository.findVoteCountsByPostId(postId);
    }
}
