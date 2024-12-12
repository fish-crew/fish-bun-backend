package fish.common.calendar.repository;

import fish.common.detail.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<DetailEntity, Long> {
    List<DetailEntity> findAllByUserId(Long userId);
}
