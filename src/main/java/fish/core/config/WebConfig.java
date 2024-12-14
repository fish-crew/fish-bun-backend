package fish.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://bunglog.me") // 프론트엔드 URL (로컬 테스트 시 http://localhost:3000 같은 URL)
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization"); // Authorization 헤더 노출
            }
        };
    }
}
