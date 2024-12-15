package fish.core.config;

import fish.core.oauth.service.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final OAuthUserService oAuthUserService;
    private final OAuth2TokenFilter oAuth2TokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, LoginAuthHandler authHandler) throws Exception {
        http.cors(cors -> cors
                .configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("http://localhost:3001", "https://bunglog.me"));
                    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setAllowCredentials(true);
                    return configuration;
                })
        );

        http.csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                        "/fish-bun/**" // CSRF 비활성화 경로
                )
        );

        http.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers
                            ("/login/**"
                                    , "/oauth2/**"
                                    , "/css/**"
                                    , "/images/**"
                                    , "/js/**"
                            ).permitAll() // 인증 없이 접근 가능
                    .requestMatchers("/fish-bun/**").authenticated()
            ;
        });

        http.oauth2Login(form -> {
            form
                    .userInfoEndpoint(userInfoEndpointConfig -> {
                        userInfoEndpointConfig.userService(oAuthUserService);
                    })
                    .successHandler(authHandler)
                    .failureHandler(authHandler);
        });
        http.addFilterBefore(
                oAuth2TokenFilter, OAuth2AuthorizationRequestRedirectFilter.class
        );

        return http.build();
    }
}
