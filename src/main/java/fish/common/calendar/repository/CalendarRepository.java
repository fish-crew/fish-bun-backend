package fish.common.calendar.repository;

import fish.common.detail.entity.DetailEntity;
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
            "WHERE YEAR(`date`) = ?1 AND MONTH(`date`) = ?2 AND F.userId = ?3", nativeQuery = true)
    List<Map<String, Object>> findAllByUserId(int year, int month, Long userId);

    @Query(value = "SELECT F.flavors " +
                    "FROM FISH_BUN_DETAIL F " +
                    "WHERE YEAR(`date`) = ?1 AND MONTH(`date`) = ?2 AND F.userId = ?3", nativeQuery = true)
    List<String> getMonthlyCountByMonth(int year, int month, Long userId);

    Optional<DetailEntity> findByIdAndUserId(Long id, Long userId);
}