package fish.common.calendar.repository;

import fish.common.detail.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<DetailEntity, Long> {
    List<DetailEntity> findAllByUserId(Long userId);

    @Query(value = "SELECT F.flavors " +
                    "FROM FISH_BUN_DETAIL F " +
                    "WHERE YEAR(`date`) = ?1 AND MONTH(`date`) = ?2 AND F.userId = ?3", nativeQuery = true)
    List<String> getMonthlyCountByMonth(int year, int month, Long userId);
}
