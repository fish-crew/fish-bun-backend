package fish.user.community.comment.index.service;

import fish.user.community.comment.index.dto.request.CommentRequest;
import fish.user.community.comment.index.dto.response.CommentResponse;
import fish.user.community.comment.index.entity.CommentEntity;
import fish.user.community.comment.index.repository.CommentRepository;
import fish.user.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long saveComment(Long postId, CommentRequest request, User user) {
        CommentEntity entity = CommentEntity.toEntity(postId, request, user);
        return commentRepository.save(entity).getId();
    }

    public List<CommentResponse> findAllComments(Long postId, Long userId) {
        List<Map<String, Object>> comments = commentRepository.findCommentsByPostId(postId, userId);

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
}
