package fish.common.main.repository;

import fish.common.detail.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface MainRepository extends JpaRepository<DetailEntity, Long> {
    @Query(value = "SELECT COUNT(*) AS weeklyCount, " +
                    "JSON_OBJECTAGG(DAYNAME(STR_TO_DATE(`date`, '%Y-%m-%d')), id) AS daysInWeek " +
                    "FROM FISH_BUN_DETAIL " +
                    "WHERE userId = ?1 AND STR_TO_DATE(`date`, '%Y-%m-%d') BETWEEN " +
                    "DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) AND " +
                    "DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 6 DAY)", nativeQuery = true)
    Map<String, Object> countCurrentWeekData(Long userId);

    @Query(value = "SELECT COUNT(*) " +
            "FROM FISH_BUN_DETAIL " +
            "WHERE userID = ?1 " +
            "AND DATE_FORMAT(STR_TO_DATE(`date`, '%Y-%m-%d'), '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')",
            nativeQuery = true)
    int countCurrentMonthData(Long userId);
}
