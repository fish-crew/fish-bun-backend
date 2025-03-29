package fish.common.community.post.repository;

import fish.common.community.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query(value = """
            SELECT
                S.id,
                S.firstOption,
                S.secondOption,
                S.title,
                S.contents,
                S.regDate,
                S.fileIdList,
                IFNULL(SUM(CASE WHEN V.voteOption = S.firstOption THEN 1 ELSE 0 END), 0) AS firstOptionCount,
                IFNULL(SUM(CASE WHEN V.voteOption = S.secondOption THEN 1 ELSE 0 END), 0) AS secondOptionCount,
                MAX(CASE WHEN U.id = :userId THEN V.voteOption ELSE NULL END) AS selectedOption
            FROM POST S
            LEFT JOIN VOTE V ON S.id = V.postId
            LEFT JOIN USER U ON V.userId = U.id
            WHERE S.id = :postId
            GROUP BY S.id
            """, nativeQuery = true)
    Map<String, Object> getStatsByPostId(Long userId, Long postId);
}
