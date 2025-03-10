package fish.common.community.comment.likes.repository;

import fish.common.community.comment.likes.entity.CommentLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikesEntity, Long> {
    Optional<CommentLikesEntity> findByCommentIdAndUserId(Long commentId, Long userId);
}
