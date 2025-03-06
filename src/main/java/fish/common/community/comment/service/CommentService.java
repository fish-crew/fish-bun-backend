package fish.common.community.comment.service;

import fish.common.community.comment.dto.request.CommentRequest;
import fish.common.community.comment.dto.response.CommentResponse;
import fish.common.community.comment.entity.CommentEntity;
import fish.common.community.comment.entity.CommentLikesEntity;
import fish.common.community.comment.repository.CommentLikesRepository;
import fish.common.community.comment.repository.CommentRepository;
import fish.common.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    public Long saveComment(Long postId, CommentRequest request, User user) {
        CommentEntity entity = CommentEntity.toEntity(postId, request, user);
        return commentRepository.save(entity).getId();
    }

    public List<CommentResponse> findAllComments(Long postId) {
        List<Map<String, Object>> comments = commentRepository.findCommentsByPostId(postId);
        return comments.stream().map(CommentResponse::toResponse).toList();
    }

    @Transactional
    public void modifyComment(Long commentId, CommentRequest request) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment data not found with id: " + commentId));

        entity.modifyContents(request.getContents());
        commentRepository.save(entity);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

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
