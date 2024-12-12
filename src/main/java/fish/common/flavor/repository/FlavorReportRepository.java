package fish.common.flavor.repository;

import fish.common.flavor.entity.FishBunFlavorReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlavorReportRepository extends JpaRepository<FishBunFlavorReport, Long> {
}
