package fish.common.fishBun.repository;

import fish.common.fishBun.entity.FishBunCalendar;
import fish.common.fishBun.entity.FishBunFlavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishBunCalendarRepository extends JpaRepository<FishBunCalendar, Long> {
    List<FishBunCalendar> findAllByUserId(Long id);

    @Query("SELECT f " +
            "FROM FishBunFlavor f JOIN CalendarFlavor cf ON cf.fishBunFlavor.id = f.id " +
            "WHERE cf.fishBunCalendar.id = :calendarId")
    List<FishBunFlavor> findTodayFlavorsByCalendarId(@Param("calendarId") Long calendarId);
}
