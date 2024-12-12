package fish.common.main.entity.repository;

import fish.common.main.entity.FishBunDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface MainRepository extends JpaRepository<FishBunDetail, Long> {
    @Query(value = "SELECT COUNT(*) AS weeklyCount, GROUP_CONCAT(DAYNAME(`date`)) AS days " +
                    "FROM FISH_BUN_DETAIL " +
                    "WHERE userId = ?1 AND `date` BETWEEN " +
                    "DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) AND " +
                    "DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 7 DAY)", nativeQuery = true)
    Map<String, Object> countCurrentWeekData(Long userId);
}
