package fish.user.community.vote.repository;

import fish.user.community.vote.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    @Query(value = """
            SELECT VOTE.voteOption, IFNULL(COUNT(*), 0) AS voteCount
            FROM VOTE
            WHERE VOTE.postId = :postId
            GROUP BY VOTE.voteOption
            """, nativeQuery = true)
    List<Map<String, Object>> findVoteCountsByPostId(@Param("postId") Long postId);

    @Query(value = """
            SELECT IFNULL(COUNT(*), 0)
            FROM VOTE
            WHERE VOTE.postId = :postId
            """, nativeQuery = true)
    int findTotalVoteCountByPostId(@Param("postId") Long postId);

    Optional<VoteEntity> findByPostIdAndUserId(Long postId, Long userId);
}
