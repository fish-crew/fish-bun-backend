package fish.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 구독(sub)
        config.enableSimpleBroker("/topic");
        // 발행(pub
        config.setApplicationDestinationPrefixes("/ws-community");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("https://bunglog.me", "http://localhost:3000", "http://localhost:8080")
                .withSockJS();
    }
}
