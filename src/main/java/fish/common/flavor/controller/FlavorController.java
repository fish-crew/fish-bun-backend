package fish.common.flavor.controller;

import fish.common.flavor.request.FlavorReportRequest;
import fish.common.flavor.response.FlavorResponse;
import fish.common.flavor.service.FlavorService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun")
public class FlavorController {
    private final FlavorService flavorService;

    @GetMapping(value = "/flavors")
    public ResponseEntity<ResponseUtil<List<FlavorResponse>>> getFlavorList() {
        List<FlavorResponse> data = flavorService.findAllFlavorsOrderBySeq();
        return ResponseEntity.ok(ResponseUtil.success(data));
    }

    @PostMapping(value = "/report")
    public ResponseEntity report(@RequestBody FlavorReportRequest request, @AuthenticationPrincipal User user) {
        List<String> flavors = Arrays.stream(request.getFlavors().split(",\\s*")).map(String::trim).toList();
        for (String flavor: flavors) {
            flavorService.saveReportData(flavor, user.getId());
        }

        return ResponseEntity.ok(ResponseUtil.success());
    }
}
