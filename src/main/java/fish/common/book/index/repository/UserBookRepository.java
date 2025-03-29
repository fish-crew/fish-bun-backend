package fish.common.book.index.repository;

import fish.common.book.index.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserBookRepository extends JpaRepository<UserBookEntity, Long> {
    List<UserBookEntity> findAllByUserId(Long userId);

    @Query(value = """
            SELECT JSON_OBJECT('id', aggregated_data.id, 'date', aggregated_data.date, 'count', aggregated_data.total_count) AS jsonData
            FROM (
                SELECT FBD.id, FBD.date, SUM(jt.count) AS total_count
                FROM FISH_BUN_DETAIL FBD,
                JSON_TABLE(FBD.flavors, '$[*]'
                COLUMNS (
                   flavorId INT PATH '$.flavorId',
                   count INT PATH '$.count'
                )) AS jt
                WHERE jt.flavorId = :flavorId AND FBD.userId = :userId
                GROUP BY FBD.id, FBD.date
                ORDER BY FBD.date ASC 
            ) AS aggregated_data
            """, nativeQuery = true)
    List<String> findUserBookDetail(@Param("userId") Long userId, @Param("flavorId") Long flavorId);

    @Query(value = """
        SELECT
            FBF.id, FBF.flavor, FBF.iconCode, FBF.seq, FBF.regDate, FBF.description, FBF.highlight,
            UFBB.rating AS myRating,
            ROUND(AVG(UFBB_ALL.rating), 2) AS avgRating,
            COUNT(UFBB_ALL.id) AS ratingCount
        FROM FISH_BUN_FLAVOR FBF
        LEFT JOIN USER_FISH_BUN_BOOK UFBB
        ON FBF.id = UFBB.completedFlavorId AND UFBB.userId = :userId
        LEFT JOIN USER_FISH_BUN_BOOK UFBB_ALL
        ON FBF.id = UFBB_ALL.completedFlavorId AND UFBB_ALL.rating IS NOT NULL
        WHERE FBF.id = :flavorId
        GROUP BY FBF.id, UFBB.rating
        """, nativeQuery = true
    )
    Map<String, Object> findUserBookRatingDetail(@Param("userId") Long userId, @Param("flavorId") Long flavorId);

    Optional<UserBookEntity> findByUserIdAndCompletedFlavorId(Long userId, Long flavorId);
}