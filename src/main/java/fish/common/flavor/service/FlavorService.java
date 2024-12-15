package fish.common.flavor.service;

import fish.common.flavor.entity.FishBunFlavorReport;
import fish.common.flavor.repository.FlavorReportRepository;
import fish.common.flavor.repository.FlavorRepository;
import fish.common.flavor.response.FlavorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlavorService {
    private final FlavorRepository fishBunFlavorRepository;
    private final FlavorReportRepository flavorReportRepository;

    public List<FlavorResponse> findAllFlavors() {
        return fishBunFlavorRepository.findAll()
                .stream()
                .map(FlavorResponse::toResponseDTO)
                .toList();
    }

    public List<FlavorResponse> findAllFlavors1() {
        return fishBunFlavorRepository.findAll()
                .stream()
                .map(FlavorResponse::toResponseDTO)
                .toList();
    }



    public void saveReportData(String flavor, Long userId) {
        FishBunFlavorReport flavorReport = FishBunFlavorReport.toEntity(flavor, userId);
        flavorReportRepository.save(flavorReport);
    }
}
