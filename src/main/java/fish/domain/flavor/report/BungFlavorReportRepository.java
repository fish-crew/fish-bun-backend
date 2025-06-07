package fish.domain.flavor.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

@Repository
public interface BungFlavorReportRepository extends JpaRepository<BungFlavorReportEntity, Long> {
    @Query(value = "SELECT FBFR.flavor AS flavor, FBFR.status AS status, U.nickname AS nickname " +
            "FROM BUNG_FLAVOR_REPORT FBFR " +
            "LEFT JOIN USER U ON FBFR.userId = U.id", nativeQuery = true)
    List<Map<String, Object>> findAllReports();
}
