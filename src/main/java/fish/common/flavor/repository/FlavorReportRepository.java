package fish.common.flavor.repository;

import fish.common.flavor.entity.FishBunFlavorReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

@Repository
public interface FlavorReportRepository extends JpaRepository<FishBunFlavorReport, Long> {
    @Query(value = "SELECT FBFR.flavor AS flavor, FBFR.status AS status, U.nickname AS nickname " +
            "FROM FISH_BUN_FLAVOR_REPORT FBFR " +
            "LEFT JOIN USER U ON FBFR.userId = U.id", nativeQuery = true)
    List<Map<String, Object>> findAllReports();
}
