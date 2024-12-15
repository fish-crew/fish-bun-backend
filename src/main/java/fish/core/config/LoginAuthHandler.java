package fish.core.config;

import fish.common.history.user.entity.UserHistory;
import fish.common.history.user.serivce.UserHistoryService;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import fish.core.util.IpAddressUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginAuthHandler extends SimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private final UserHistoryService userHistoryService;
    private final UserService userService;
    private final OAuth2AuthorizedClientService authorizedClientService;
    @Value("${front-url}")
    private String frontUrl;
    private final static String MAIN_URL = "/main";
    private final static String NICKNAME_URL = "/nicknamePage";
    private final static Integer LOGIN_SESSION = 6 * 60 * 60;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        long providerId = Long.parseLong(authentication.getName());
        //로그인 이력 히스토리 쌓음
        userHistoryService.saveHistory(
                new UserHistory(providerId , IpAddressUtil.getClientIp(request)
                )
        );
        //유저 값 가져와서
        User user = userService.getUserById(providerId);
        response.addCookie(setCookie(authentication));
        //유저가 닉네임이 등록되어있을 시에 Main으로 리턴, 존재하지 않을 시 Nickname 등록 URL로 리턴
        String path = user.getNickname() != null ? MAIN_URL : NICKNAME_URL;
        String redirectUrl = frontUrl + path;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private Cookie setCookie(Authentication authentication) {
        // 리다이렉트 처리 (예: 홈 페이지로 이동)
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        // 클라이언트 정보 가져오기
        String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
        // OAuth2AuthorizedClient 가져오기
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                clientRegistrationId, oauthToken.getName());
        // Access Token을 HttpOnly Cookie로 저장
        Cookie cookie = new Cookie("accessToken", authorizedClient.getAccessToken().getTokenValue());
        cookie.setHttpOnly(false);
        cookie.setSecure(true); // HTTPS 환경에서만 전송 (필수)
        cookie.setPath("/");
        cookie.setMaxAge(LOGIN_SESSION); // 6시간 유효
        return cookie;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException {
        response.sendRedirect("/loginPage");
    }
}
