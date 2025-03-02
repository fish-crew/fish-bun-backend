package fish.common.community.comment.service;

import fish.common.community.comment.dto.request.CommentRequest;
import fish.common.community.comment.dto.response.CommentResponse;
import fish.common.community.comment.entity.CommentEntity;
import fish.common.community.comment.repository.CommentRepository;
import fish.common.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long saveComment(Long postId, CommentRequest request, User user) {
        CommentEntity entity = CommentEntity.toEntity(postId, request, user);
        return commentRepository.save(entity).getId();
    }

    public List<CommentResponse> findAllComments(Long postId) {
        List<CommentEntity> commentEntities = commentRepository.findAllByPostId(postId);
        return commentEntities.stream().map(CommentResponse::toResponse).toList();
    }

    @Transactional
    public void modifyComment(Long commentId, CommentRequest request) {
        CommentEntity entity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment data not found with id: " + commentId));

        entity.modifyContents(request.getContents());
        commentRepository.save(entity);
    }
}
