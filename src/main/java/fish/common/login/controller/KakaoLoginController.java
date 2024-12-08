package fish.common.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fish.common.login.service.KakaoLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.UnknownHostException;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class KakaoLoginController {

    private final KakaoLoginService loginService;
    @Value("${kakao.send-oauth-uri}")
    private String oauthUri;
    @Value("${kakao.redirect-uri}")
    private String redirectUri;
    @Value("${kakao.js-client-id}")
    private String clientId;
    @Value("${kakao.scope}")
    private String scope;

    @GetMapping()
    public ModelAndView home() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("views/login");
        mnv.addObject("oauthUri", oauthUri);
        mnv.addObject("scope", scope);
        mnv.addObject("clientId", clientId);
        mnv.addObject("redirectUri", redirectUri);
        return mnv;
    }

    @GetMapping(value = "/kakao")
    public void login(@RequestParam("code") String authorizationCode, HttpServletRequest request)
            throws JsonProcessingException, UnknownHostException {
        loginService.login(authorizationCode, request);
    }

    @GetMapping(value = "/kakao-logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView mnv = new ModelAndView();
        HttpSession session = request.getSession();
        loginService.logout(request);
        session.invalidate();
        mnv.setViewName("views/login");
        return mnv;
    }
}
