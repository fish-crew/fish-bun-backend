package fish.common.community.comment.repository;

import fish.common.community.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query(value = """
            SELECT c.id, c.contents, c.userNickName, IFNULL(COUNT(cl.id), 0) AS likeCount, c.regDate
            FROM COMMENT c LEFT JOIN COMMENT_LIKES cl ON c.id = cl.commentId
            WHERE c.postId = :postId
            GROUP BY c.id;
            """, nativeQuery = true)
    List<Map<String, Object>> findCommentsByPostId(@Param("postId") Long postId);
}
