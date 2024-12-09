package fish.common.login.controller;

import fish.core.oauth.dto.AuthUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class KakaoLoginController {

    @GetMapping()
    public ModelAndView home() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("views/login");
        return mnv;
    }

    @GetMapping(value = "/success")
    public ModelAndView logout(@AuthenticationPrincipal AuthUserInfo userInfo) {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("views/loginSuccess");
        return mnv;
    }

    @GetMapping(value = "/kakao-logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView mnv = new ModelAndView();
        HttpSession session = request.getSession();
        session.invalidate();
        mnv.setViewName("views/login");
        return mnv;
    }
}
