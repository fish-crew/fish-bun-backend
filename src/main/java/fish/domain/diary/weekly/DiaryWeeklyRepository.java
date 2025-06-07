package fish.domain.diary.weekly;

import fish.domain.diary.index.BungDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DiaryWeeklyRepository extends JpaRepository<BungDiary, Long> {
    @Query(value = "SELECT COUNT(*) AS weeklyCount, " +
                    "JSON_OBJECTAGG(DAYNAME(STR_TO_DATE(`date`, '%Y-%m-%d')), id) AS daysInWeek " +
                    "FROM BUNG_DIARY " +
                    "WHERE userId = ?1 AND STR_TO_DATE(`date`, '%Y-%m-%d') BETWEEN " +
                    "DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) AND " +
                    "DATE_ADD(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 6 DAY)", nativeQuery = true)
    Map<String, Object> countCurrentWeekData(Long userId);

    @Query(value = "SELECT COUNT(*) " +
            "FROM BUNG_DIARY " +
            "WHERE userID = ?1 " +
            "AND DATE_FORMAT(STR_TO_DATE(`date`, '%Y-%m-%d'), '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')",
            nativeQuery = true)
    int countCurrentMonthData(Long userId);
}
