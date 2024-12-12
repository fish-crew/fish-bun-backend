package fish.common.flavor.controller;

import fish.common.flavor.request.FlavorReportRequest;
import fish.common.flavor.response.FlavorResponse;
import fish.common.flavor.service.FlavorService;
import fish.core.oauth.dto.AuthUserInfo;
import fish.core.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fish-bun")
public class FlavorController {
    private final FlavorService flavorService;

    @GetMapping(value = "/flavors")
    public ResponseEntity<ResponseUtil<List<FlavorResponse>>> getFlavorList() {
        List<FlavorResponse> data = flavorService.findAllFlavors();
        return ResponseEntity.ok(ResponseUtil.success(data));
    }

    @PostMapping(value = "/report")
    public ResponseEntity report(@RequestBody FlavorReportRequest request, @AuthenticationPrincipal AuthUserInfo authUserInfo) {
        List<String> flavors = Arrays.stream(request.getFlavors().split(",\\s*")).map(String::trim).toList();
        for (String flavor: flavors) {
            flavorService.saveReportData(flavor, authUserInfo.getUser().getId());
        }

        return ResponseEntity.ok(ResponseUtil.success());
    }
}
