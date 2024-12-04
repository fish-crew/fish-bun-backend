package fish.common.fishBun.repository;

import fish.common.fishBun.entity.FishBunCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishBunCalendarRepository extends JpaRepository<FishBunCalendar, Long> {
    List<FishBunCalendar> findAllByUserId(Long id);
}
