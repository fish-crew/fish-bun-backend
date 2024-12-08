package fish.common.calendar.repository;

import fish.common.calendar.entity.FishBunCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<FishBunCalendar, Long> {
    List<FishBunCalendar> findAllById(Long id);
}
