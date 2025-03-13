package fish.common.bungbal.controller;

import fish.common.bungbal.dto.request.BungbalRequest;
import fish.common.bungbal.service.BungbalService;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bungbal/stats")
@Tag(name = "붕어빵 취향 테스트 API", description = "로그인 없이도 사용 가능한 API")
public class BungbalController {
    public final BungbalService bungbalService;

    @Operation(summary = "취향 테스트 mbti 조회")
    @GetMapping()
    public ResponseUtil getStats() {
        Map<Object, Object> additionalData = new HashMap<>();
        additionalData.put("total", bungbalService.getTotalCount());
        return ResponseUtil.success(bungbalService.getStats(), additionalData);
    }

    @Operation(summary = "취향 테스트 mbti 카운트", description = "테스트 결과 MBTI 집계를 위한 API ")
    @PostMapping("/count")
    public ResponseUtil count(@RequestBody BungbalRequest request) {
        bungbalService.count(request.getMbti());
        return ResponseUtil.success();
    }
}
