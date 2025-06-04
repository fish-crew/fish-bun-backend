package fish.user.calendar.repository;

import fish.user.detail.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<DetailEntity, Long> {
    @Query(value = "SELECT F.id, F.date " +
            "FROM FISH_BUN_DETAIL F " +
            "WHERE DATE_FORMAT(F.date, '%Y-%m') =?1 AND F.userId = ?2", nativeQuery = true)
    List<Map<String, Object>> findAllByUserId(String date, Long userId);

    @Query("SELECT f " +
            "FROM DetailEntity f " +
            "WHERE  DATE_FORMAT(f.date, '%Y-%m') =?1 AND f.userId = ?2")
    List<DetailEntity> getMonthlyCountByMonth(String date, Long userId);

    Optional<DetailEntity> findByIdAndUserId(Long id, Long userId);
}