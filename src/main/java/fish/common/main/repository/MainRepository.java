package fish.common.main.repository;

import fish.common.detail.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface MainRepository extends JpaRepository<DetailEntity, Long> {
    @Query(value = "SELECT COUNT(*) AS weeklyCount, GROUP_CONCAT(DAYNAME(`date`)) AS days " +
                    "FROM FISH_BUN_DETAIL " +
                    "WHERE userId = ?1 AND `date` BETWEEN " +
                    "DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) AND " +
                    "DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 7 DAY)", nativeQuery = true)
    Map<String, Object> countCurrentWeekData(Long userId);

    @Query(value = "SELECT COUNT(*) " +
                    "FROM FISH_BUN_DETAIL " +
                    "WHERE userID =?1 AND MONTH(`date`) = MONTH(CURDATE()) AND YEAR(`date`) = YEAR(CURDATE())", nativeQuery = true)
    int countCurrentMonthData(Long userId);
}
