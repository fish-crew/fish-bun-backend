package fish.common.flavor.controller;

import fish.common.flavor.response.FlavorResponse;
import fish.common.flavor.service.FlavorService;
import fish.common.response.FishBunResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun")
public class FlavorController {
    private final FlavorService flavorService;

    @GetMapping(value = "/flavors")
    public ResponseEntity<FishBunResponse<List<FlavorResponse>>> getFlavorList() {
        List<FlavorResponse> data = flavorService.findAllFlavors();
        return ResponseEntity.ok(new FishBunResponse<>(data));
    }
}
