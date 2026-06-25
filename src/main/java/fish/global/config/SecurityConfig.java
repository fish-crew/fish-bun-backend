package fish.global.config;

import fish.global.oauth.service.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final OAuthUserService oAuthUserService;
    private final OAuth2TokenFilter oAuth2TokenFilter;

    @Value("${app.cors.allowed-origins}")
    private List<String> allowedOrigins;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, LoginAuthHandler authHandler) throws Exception {
        http.cors(cors -> cors
                .configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(allowedOrigins);
                    configuration.setAllowedMethods(List.of(
                            "GET", "POST", "PUT",
                            "PATCH", "DELETE", "OPTIONS"
                    ));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setAllowCredentials(true);
                    return configuration;
                })
        );

        http.csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                        "/fish-bun/**",
                        "/admin/**",
                        "/bungbal/stats/**"
                )
        );

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                        "/login/**",
                        "/oauth2/**",
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/admin/**",
                        "/bungbal/stats/**",
                        "/ws/**",
                        "/api-test/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()
                .requestMatchers("/fish-bun/**").authenticated()
        );

        http.oauth2Login(form -> form
                .userInfoEndpoint(userInfoEndpointConfig ->
                        userInfoEndpointConfig.userService(oAuthUserService)
                )
                .successHandler(authHandler)
                .failureHandler(authHandler)
        );

        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .defaultAuthenticationEntryPointFor(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        new AntPathRequestMatcher("/fish-bun/**")
                )
        );

        http.addFilterBefore(
                oAuth2TokenFilter, OAuth2AuthorizationRequestRedirectFilter.class
        );

        return http.build();
    }
}
