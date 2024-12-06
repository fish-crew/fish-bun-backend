package fish.core.config;

import fish.core.oauth.service.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final OAuthUserService oAuthUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginAuthHandler loginAuthHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers
                                ("/login/**"
                                        , "/oauth2/**"
                                        , "/css/**"
                                        , "/images/**"
                                        , "/js/**"
                                ).permitAll() // 인증 없이 접근 가능
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .formLogin(login -> login
                        .loginPage("/login") // Kakao 로그인 페이지
                        .loginProcessingUrl("/login/success")
                        .successHandler(loginAuthHandler) // 인증 성공 핸들러 등록
                        .failureHandler(loginAuthHandler)
                        )
                        .logout(logout -> logout
                        .logoutUrl("/logout")  // 로그아웃 URL (기본값은 /logout)
                        .logoutSuccessUrl("/login")  // 로그아웃 후 리다이렉트될 URL
                        .invalidateHttpSession(true)  // 세션 무효화
                        .clearAuthentication(true)  // 인증 정보 제거
                        .deleteCookies("JSESSIONID"
                    )
                );// JSESSIONID 쿠키 삭제
        return http.build();
    }
}
