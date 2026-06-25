package fish.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${swagger.localUrl:http://localhost:8080}")
    private String localUrl;

    @Value("${swagger.prodUrl}")
    private String prodUrl;

    @Bean
    public GroupedOpenApi adminGroup() {
        List<Tag> tags = List.of(
                new Tag().name("[관리자] Login API"),
                new Tag().name("[관리자] 기능 API")
        );

        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**")
                .addOpenApiCustomizer(openApi -> openApi.setTags(tags))
                .build();
    }

    @Bean
    public GroupedOpenApi customerGroup() {
        List<Tag> tags = List.of(
                new Tag().name("붕어빵 취향 테스트 API"),
                new Tag().name("붕어빵 사용자 API"),
                new Tag().name("붕어빵 가게 API"),
                new Tag().name("붕어빵 맛 API"),
                new Tag().name("붕어빵 등록 API"),
                new Tag().name("커뮤니티 API"),
                new Tag().name("붕어빵 캘린더 API"),
                new Tag().name("붕어빵 도감 API"),
                new Tag().name("붕어빵 메인 API")
                );

        return GroupedOpenApi.builder()
                .group("customer")
                .pathsToMatch("/fish-bun/**", "/bungbal/**")
                .addOpenApiCustomizer(openApi -> openApi.setTags(tags))
                .build();
    }


    @Bean
    public OpenAPI openAPI() {
        String key = "Access Token (without Bearer)";

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(key);

        SecurityScheme accessTokenSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        Components components = new Components().addSecuritySchemes(key, accessTokenSecurityScheme);

        return new OpenAPI()
                .info(apiInfo())
                .addSecurityItem(securityRequirement)
                .components(components)
                .addServersItem(new Server().url(localUrl).description("Local Swagger API"))
                .addServersItem(new Server().url(prodUrl).description("Product Swagger API"));
    }

    private Info apiInfo() {
        return new Info()
                .title("Bunglog API Test") // API의 제목
                .version("1.0.0"); // API의 버전
    }
}
