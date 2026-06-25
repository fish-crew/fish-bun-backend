package fish.admin.user.controller;

import fish.common.user.service.UserService;
import fish.global.dto.BasePageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@Tag(name = "[관리자] 기능 API")
public class AdminUserController {
    private final UserService userService;

    @GetMapping("/user/list")
    public String viewUser() {
        return "main/user/list";
    }

    @Operation(summary = "[관리자] 유저 리스트 조회")
    @GetMapping(value = "/user/list.json")
    @ResponseBody
    public ResponseEntity<?> listUser(BasePageDto baseDto) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", userService.getList(baseDto));
        return ResponseEntity.ok().body(result);
    }
}
