package fish.common.book.repository;

import fish.common.book.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookRepository extends JpaRepository<UserBookEntity, Long> {
    List<UserBookEntity> findAllByUserId(Long userId);

    @Query(value = "SELECT JSON_OBJECT('id', aggregated_data.id, 'date', aggregated_data.date, 'count', aggregated_data.total_count) AS jsonData " +
            "FROM ( " +
            "    SELECT FBD.id, FBD.date, SUM(jt.count) AS total_count " +
            "    FROM FISH_BUN_DETAIL FBD, " +
            "    JSON_TABLE(FBD.flavors, '$[*]' " +
            "    COLUMNS (" +
            "       flavorId INT PATH '$.flavorId', " +
            "       count INT PATH '$.count' " +
            "    )) AS jt " +
            "    WHERE jt.flavorId = :flavorId AND FBD.userId = :userId " +
            "    GROUP BY FBD.id, FBD.date " +
            "    ORDER BY FBD.date ASC " +
            ") AS aggregated_data", nativeQuery = true)
    List<String> findUserBookDetail(@Param("userId") Long userId, @Param("flavorId") Long flavorId);
}