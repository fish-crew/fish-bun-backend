package fish.admin.main.controller;

import fish.common.flavor.service.FlavorService;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {
    private final FlavorService flavorService;
    private final UserService userService;

    @GetMapping(value = "/report/list")
    public String viewReport() {
        return "main/report/list";
    }
    @GetMapping(value = "/report/list.json")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> listReport() {
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("data", flavorService.findAllReports());
        return result;
    }

    @GetMapping("/user/list")
    public String viewUser() {
        return "main/user/list";
    }

    @GetMapping(value = "/user/list.json")
    @ResponseBody
    public Map<String, List<User>> listUser() {
        Map<String, List<User>> result = new HashMap<>();
        result.put("data", userService.findAllUsers());
        return result;
    }
}
