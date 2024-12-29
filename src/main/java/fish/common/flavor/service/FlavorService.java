package fish.common.flavor.service;

import fish.common.flavor.entity.FishBunFlavorReport;
import fish.common.flavor.repository.FlavorReportRepository;
import fish.common.flavor.repository.FlavorRepository;
import fish.common.flavor.response.FlavorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FlavorService {
    private final FlavorRepository fishBunFlavorRepository;
    private final FlavorReportRepository flavorReportRepository;

    public List<FlavorResponse> findAllFlavorsOrderBySeq() {
        return fishBunFlavorRepository.findAllByOrderBySeqAsc()
                .stream()
                .map(FlavorResponse::toResponseDTO)
                .toList();
    }

    public List<Map<String, Object>> findAllReports() {
        return flavorReportRepository.findAllReports();
    }



    public void saveReportData(String flavor, Long userId) {
        FishBunFlavorReport flavorReport = FishBunFlavorReport.toEntity(flavor, userId);
        flavorReportRepository.save(flavorReport);
    }
}
