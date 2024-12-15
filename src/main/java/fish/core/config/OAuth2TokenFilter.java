package fish.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@WebFilter(urlPatterns = "/fish-bun/**")
@Component
public class OAuth2TokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private static final String KAKAO_TOKEN_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final RequestMatcher shouldFilterMatcher; // URL 매칭

    public OAuth2TokenFilter(UserService userService) {
        // 필터를 적용할 경로 설정
        RequestMatcher fishBunMatcher = new AntPathRequestMatcher("/fish-bun/**");
        // 두 조건 중 하나라도 만족하면 필터를 적용
        this.shouldFilterMatcher = new OrRequestMatcher(fishBunMatcher);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // URL 매칭 확인
        if (!shouldFilterMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 1. Authorization 헤더에서 Access Token 추출
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        User user = getUserInfo(token);
        if (user == null) {
            // 4. 유효하지 않은 토큰인 경우, SecurityContext 초기화
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token");
            return;
        }

       Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, // Principal 대신 Access Token
                token, // Credentials
                null
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 다음 필터로 요청을 전달
        filterChain.doFilter(request, response);
    }
    private User getUserInfo(String token) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Void> request = new HttpEntity<>(headers);

            // 카카오 API 호출로 토큰 검증
            ResponseEntity<String> entity = restTemplate.exchange(
                    KAKAO_TOKEN_INFO_URL,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseData = objectMapper.readValue(entity.getBody(), Map.class);
            return userService.getUserById((Long)responseData.get("id"));
        } catch (Exception e) {
            return null;
        }
    }
}
