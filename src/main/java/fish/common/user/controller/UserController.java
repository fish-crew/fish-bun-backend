package fish.common.user.controller;

import fish.common.user.entity.User;
import fish.core.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun")
public class UserController {
    @GetMapping("/user-info")
    public ResponseUtil<User> getUser(@AuthenticationPrincipal User user) {
        return ResponseUtil.success(user);
    }
}
