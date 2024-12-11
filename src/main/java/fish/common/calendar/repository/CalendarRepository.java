package fish.common.calendar.repository;

import fish.common.main.entity.FishBunDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<FishBunDetail, Long> {
    List<FishBunDetail> findAllByUserId(Long userId);
}
