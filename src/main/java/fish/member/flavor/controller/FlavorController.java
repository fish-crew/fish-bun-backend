package fish.member.flavor.controller;

import fish.member.flavor.dto.FlavorReportRequest;
import fish.member.flavor.dto.FlavorResponse;
import fish.member.flavor.service.BungFlavorService;
import fish.domain.user.index.User;
import fish.common.util.ResponseUtil;
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
@RequestMapping(value = "/member")
@Tag(name = "붕어빵 맛 API")
public class FlavorController {
    private final BungFlavorService bungFlavorService;

    @Operation(summary = "붕어빵 맛 전체 조회")
    @GetMapping(value = "/flavors")
    public ResponseEntity<ResponseUtil<List<FlavorResponse>>> getFlavorList() {
        List<FlavorResponse> data = bungFlavorService.findAllFlavorsOrderBySeq();
        return ResponseEntity.ok(ResponseUtil.success(data));
    }

    @Operation(summary = "붕어빵 맛 제보")
    @PostMapping(value = "/report")
    public ResponseEntity report(@RequestBody FlavorReportRequest request, @AuthenticationPrincipal User user) {
        List<String> flavors = Arrays.stream(request.getFlavors().split(",\\s*")).map(String::trim).toList();
        for (String flavor: flavors) {
            bungFlavorService.saveReportData(flavor, user.getId());
        }

        return ResponseEntity.ok(ResponseUtil.success());
    }
}
