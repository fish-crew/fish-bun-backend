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
    public SecurityFilterChain filterChain(HttpSecurity http, LoginAuthHandler authHandler) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

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
        return http.build();
    }
}
