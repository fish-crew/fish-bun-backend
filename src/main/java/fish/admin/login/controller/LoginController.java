package fish.admin.login.controller;

import fish.common.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class LoginController {

    @Value("${admin.id}")
    private String adminId;
    @Value("${admin.password}")
    private String adminPassword;

    @GetMapping(value = "/login")
    public String view() {
        return "login/loginPage";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "login/loginPage";
    }

    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> map,
                                     HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String email = map.get("email");
        String password = map.get("password");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 비밀번호 암호화
        String rawPassword = adminPassword;
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // 비밀번호 일치 여부 확인
        boolean isMatchPass = passwordEncoder.matches(password, encodedPassword);
        boolean isMatchId = adminId.equals(email);
        if (isMatchPass && isMatchId) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(18000);
            session.setAttribute("userId", email);
            result.put("result", "1");
            return result;
        }
        result.put("result", "0");
        return result;
    }
}
