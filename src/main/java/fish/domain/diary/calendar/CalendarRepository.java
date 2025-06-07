package fish.domain.diary.calendar;

import fish.domain.diary.index.BungDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<BungDiary, Long> {
    @Query(value = "SELECT F.id, F.date " +
            "FROM BUNG_DIARY F " +
            "WHERE DATE_FORMAT(F.date, '%Y-%m') =?1 AND F.userId = ?2", nativeQuery = true)
    List<Map<String, Object>> findAllByUserId(String date, Long userId);

    @Query("SELECT f " +
            "FROM BungDiary f " +
            "WHERE  DATE_FORMAT(f.date, '%Y-%m') =?1 AND f.userId = ?2")
    List<BungDiary> getMonthlyCountByMonth(String date, Long userId);

    Optional<BungDiary> findByIdAndUserId(Long id, Long userId);
}