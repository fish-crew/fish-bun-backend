package fish.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import fish.global.oauth.dto.AuthUserInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@WebFilter(urlPatterns = "/fish-bun/**")
@Component
public class OAuth2TokenFilter extends OncePerRequestFilter {

    private static final String KAKAO_TOKEN_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    private final UserService userService;
    private final RequestMatcher shouldFilterMatcher;

    public OAuth2TokenFilter(UserService userService) {
        this.shouldFilterMatcher = new AntPathRequestMatcher("/fish-bun/**");
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!shouldFilterMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        Object sessionSecurityContext = request.getSession(false) != null
                ? request.getSession(false).getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY)
                : null;

        if (sessionSecurityContext instanceof SecurityContext securityContext) {
            Authentication sessionAuthentication = securityContext.getAuthentication();
            if (sessionAuthentication != null && sessionAuthentication.isAuthenticated()) {
                User sessionUser = resolveSessionUser(sessionAuthentication);
                if (sessionUser != null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            sessionUser,
                            sessionAuthentication.getCredentials(),
                            sessionAuthentication.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = authHeader.substring(7);
        User user = getUserInfo(token);
        if (user == null) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token");
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                token,
                null
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private User getUserInfo(String token) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<String> entity = restTemplate.exchange(
                    KAKAO_TOKEN_INFO_URL,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseData = objectMapper.readValue(entity.getBody(), Map.class);
            return userService.getUserByProviderId(responseData.get("id").toString());
        } catch (Exception e) {
            return null;
        }
    }

    private User resolveSessionUser(Authentication sessionAuthentication) {
        Object principal = sessionAuthentication.getPrincipal();
        if (principal instanceof User user) {
            return user;
        }

        if (principal instanceof AuthUserInfo authUserInfo) {
            return authUserInfo.getUser();
        }

        String providerId = sessionAuthentication.getName();
        if (providerId == null || providerId.isBlank()) {
            return null;
        }

        return userService.getUserByProviderId(providerId);
    }
}
