package fish.user.community.comment.likes.service;

import fish.user.community.comment.likes.entity.CommentLikesEntity;
import fish.user.community.comment.likes.repository.CommentLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikesService {
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    public void saveCommentLike(Long commentId, Long userId) {
        Optional<CommentLikesEntity> entity = commentLikesRepository.findByCommentIdAndUserId(commentId, userId);

        // 이미 좋아요 눌렀다면 취소
        if (entity.isPresent()) {
            commentLikesRepository.delete(entity.get());
        } else {
            commentLikesRepository.save(CommentLikesEntity.toEntity(commentId, userId));
        }
    }
}
