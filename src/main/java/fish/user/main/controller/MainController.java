package fish.user.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fish.user.main.dto.response.FishBunDayCountResponse;
import fish.user.main.service.MainService;
import fish.user.user.entity.User;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun")
@Tag(name = "붕어빵 메인 API")
public class MainController {
    private final MainService mainService;

    @Operation(summary = "메인 주간 붕어빵 요일 조회", description = "메인 화면에서 사용자가 일주일동안 붕어빵을 먹은 요일과 전체 개수를 조회하는 API")
    @GetMapping(value = "/main")
    public ResponseUtil<FishBunDayCountResponse> countFishBunDaysInWeek(@AuthenticationPrincipal User user) throws JsonProcessingException {
        FishBunDayCountResponse data = mainService.countFishBunDaysInWeek(user.getId());
        return ResponseUtil.success(data);
    }
}
