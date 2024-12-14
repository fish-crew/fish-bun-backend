package fish.common.main.entity.controller;

import fish.common.main.entity.response.FishBunDayCountResponse;
import fish.common.main.entity.service.MainService;
import fish.common.user.entity.User;
import fish.core.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun")
public class MainController {
    private final MainService mainService;

    @GetMapping(value = "/main")
    public ResponseUtil<FishBunDayCountResponse> countFishBunDaysInWeek(@AuthenticationPrincipal User user) {
        FishBunDayCountResponse data = mainService.countFishBunDaysInWeek(user.getId());
        return ResponseUtil.success(data);
    }
}
