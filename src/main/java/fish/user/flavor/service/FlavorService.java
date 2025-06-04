package fish.user.flavor.service;

import fish.user.flavor.entity.FishBunFlavorReportEntity;
import fish.user.flavor.repository.FlavorReportRepository;
import fish.user.flavor.repository.FlavorRepository;
import fish.user.flavor.dto.response.FlavorResponse;
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
        FishBunFlavorReportEntity flavorReport = FishBunFlavorReportEntity.toEntity(flavor, userId);
        flavorReportRepository.save(flavorReport);
    }
}
