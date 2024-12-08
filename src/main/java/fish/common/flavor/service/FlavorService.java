package fish.common.flavor.service;

import fish.common.flavor.repository.FlavorRepository;
import fish.common.flavor.response.FlavorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlavorService {
    private final FlavorRepository fishBunFlavorRepository;

    public List<FlavorResponse> findAllFlavors() {
        return fishBunFlavorRepository.findAll()
                .stream()
                .map(FlavorResponse::toResponseDTO)
                .toList();
    }
}
