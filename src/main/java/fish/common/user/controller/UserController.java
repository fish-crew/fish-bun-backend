package fish.common.user.controller;

import fish.common.user.dto.UserRequest;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public ResponseUtil<User> getUser(@AuthenticationPrincipal User user) {
        return ResponseUtil.success(user);
    }

    @PostMapping("/set-nickname")
    public ResponseUtil<String> regNickname(@AuthenticationPrincipal User user
            , @RequestBody UserRequest request) {
        user.setNickname(request.getNickname());
        userService.updateUser(user);
        return ResponseUtil.success();
    }
}
