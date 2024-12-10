package fish.core.config;

import fish.common.history.user.entity.UserHistory;
import fish.common.history.user.serivce.UserHistoryService;
import fish.core.util.IpAddressUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginAuthHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private final UserHistoryService userHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String ip = IpAddressUtil.getClientIp(request);
        //로그인 이력 히스토리 쌓음
        userHistoryService.saveHistory(new UserHistory(Long.valueOf(authentication.getName()), ip));
        // 리다이렉트 처리 (예: 홈 페이지로 이동)
        response.sendRedirect("/login/success");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {

        response.sendRedirect("/login");
    }
}
