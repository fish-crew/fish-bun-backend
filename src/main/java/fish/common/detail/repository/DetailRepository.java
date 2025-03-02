package fish.common.detail.repository;

import fish.common.detail.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity, Long> {
    Optional<DetailEntity> findByIdAndUserId(Long detailId, Long userId);

    @Query(value = "SELECT " +
            "U.id AS userId, " +
            "U.nickname AS nickname, " +
            "COUNT(FBD.id) AS eatenCount, " +
            "MAX(FBD.date) AS lastEatenDate, " +
            "U.lastDate AS lastDate, " +
            "MAX(FBD.date) AS lastEatenDate, " +
            "U.providerType AS providerType, " +
            "U.regDate AS regDate " +
            "FROM USER U " +
            "LEFT JOIN FISH_BUN_DETAIL FBD " +
            "ON U.id = FBD.userId " +
            "GROUP BY U.id", nativeQuery = true)
    List<Map<String, Object>> findAllUserStats();

    @Query(value = "SELECT " +
            "U.id AS userId, " +
            "U.nickname AS nickname, " +
            "COUNT(FBD.id) AS eatenCount, " +
            "MAX(FBD.date) AS lastEatenDate, " +
            "U.lastDate AS lastDate, " +
            "MAX(FBD.date) AS lastEatenDate, " +
            "U.regDate AS regDate, " +
            "JSON_OBJECTAGG(FBD.id, FBD.date) AS dateMap " +
            "FROM USER U " +
            "LEFT JOIN FISH_BUN_DETAIL FBD " +
            "ON U.id = FBD.userId " +
            "WHERE U.id = ?1", nativeQuery = true)
    Map<String, Object> findUserStats(Long userId);
}
