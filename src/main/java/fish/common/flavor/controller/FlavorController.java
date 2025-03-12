package fish.common.flavor.controller;

import fish.common.flavor.dto.request.FlavorReportRequest;
import fish.common.flavor.dto.response.FlavorResponse;
import fish.common.flavor.service.FlavorService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun")
@Tag(name = "붕어빵 맛 API")
public class FlavorController {
    private final FlavorService flavorService;

    @Operation(summary = "붕어빵 맛 전체 조회")
    @GetMapping(value = "/flavors")
    public ResponseEntity<ResponseUtil<List<FlavorResponse>>> getFlavorList() {
        List<FlavorResponse> data = flavorService.findAllFlavorsOrderBySeq();
        return ResponseEntity.ok(ResponseUtil.success(data));
    }

    @Operation(summary = "붕어빵 맛 제보")
    @PostMapping(value = "/report")
    public ResponseEntity report(@RequestBody FlavorReportRequest request, @AuthenticationPrincipal User user) {
        List<String> flavors = Arrays.stream(request.getFlavors().split(",\\s*")).map(String::trim).toList();
        for (String flavor: flavors) {
            flavorService.saveReportData(flavor, user.getId());
        }

        return ResponseEntity.ok(ResponseUtil.success());
    }
}
