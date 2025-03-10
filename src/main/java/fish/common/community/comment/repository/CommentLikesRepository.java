package fish.common.community.comment.repository;

import fish.common.community.comment.entity.CommentLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikesEntity, Long> {
    Optional<CommentLikesEntity> findByCommentIdAndUserId(Long commentId, Long userId);

    @Query(value = """
            SELECT
                CASE
                    WHEN EXISTS (
                        SELECT 1
                        FROM COMMENT_LIKES cl
                        WHERE cl.commentId = :commentId AND cl.userId = :userId
                    ) THEN 'Y'
                    ELSE 'N'
                END
            """, nativeQuery = true)
    char isLikedByUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);
}
