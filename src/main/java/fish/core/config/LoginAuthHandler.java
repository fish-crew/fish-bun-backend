package fish.core.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginAuthHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        // 리다이렉트 처리 (예: 홈 페이지로 이동)
        response.sendRedirect("/views/loginSuccess");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        if (exception instanceof OAuth2AuthenticationException oAuth2Exception) {
            System.out.println("OAuth2 Authentication failed with error: " + oAuth2Exception.getError().getErrorCode());
            System.out.println("Error description: " + oAuth2Exception.getError().getDescription());
            System.out.println("Error URI: " + oAuth2Exception.getError().getUri());
        }
        response.sendRedirect("/login");
    }
}
