package fish.member.flavor.service;

import fish.domain.flavor.report.BungFlavorReportEntity;
import fish.domain.flavor.report.BungFlavorReportRepository;
import fish.domain.flavor.index.BungFlavorRepository;
import fish.member.flavor.dto.FlavorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BungFlavorService {
    private final BungFlavorRepository fishBunBungFlavorRepository;
    private final BungFlavorReportRepository bungFlavorReportRepository;

    public List<FlavorResponse> findAllFlavorsOrderBySeq() {
        return fishBunBungFlavorRepository.findAllByOrderBySeqAsc()
                .stream()
                .map(FlavorResponse::toResponseDTO)
                .toList();
    }

    public List<Map<String, Object>> findAllReports() {
        return bungFlavorReportRepository.findAllReports();
    }



    public void saveReportData(String flavor, Long userId) {
        BungFlavorReportEntity flavorReport = BungFlavorReportEntity.toEntity(flavor, userId);
        bungFlavorReportRepository.save(flavorReport);
    }
}
