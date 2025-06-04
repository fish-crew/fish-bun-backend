package fish.user.user.controller;

import fish.user.user.dto.request.UserRequest;
import fish.user.user.entity.User;
import fish.user.user.service.UserService;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/user")
@Tag(name = "붕어빵 사용자 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 정보 조회")
    @GetMapping("/info")
    public ResponseUtil<User> getUser(@AuthenticationPrincipal User user) {
        return ResponseUtil.success(user);
    }

    @Operation(summary = "사용자 닉네임 설정")
    @PostMapping("/set-nickname")
    public ResponseUtil<String> regNickname(@AuthenticationPrincipal User user
            , @RequestBody UserRequest request) {
        user.setNickname(request.getNickname());
        userService.updateUser(user);
        return ResponseUtil.success();
    }

    @Operation(summary = "최초 로그인 사용자 데이터 처리", description = "사용자의 최초 로그인 여부 `isFirstLogin`을 ‘N’으로 변경해주는 API")
    @PostMapping("/update-first-login")
    public ResponseUtil<String> updateFirstLogin(@AuthenticationPrincipal User user) {
        userService.updateFirstLogin(user);
        return ResponseUtil.success();
    }
}
