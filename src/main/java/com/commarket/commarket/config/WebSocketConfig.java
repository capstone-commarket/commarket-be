package com.commarket.commarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP 메시지 브로커를 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 1. 메시지 브로커(Broker) 경로 설정:
        // "/sub"로 시작하는 경로로 메시지를 전달 (구독자에게)
        config.enableSimpleBroker("/sub");

        // 2. 클라이언트에서 서버로 메시지를 보낼 때 사용할 접두사 (Application Destination Prefix)
        // "/pub"로 시작하는 메시지는 @MessageMapping 어노테이션이 붙은 메서드로 라우팅됩니다.
        // 예: /pub/chat/message
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 웹소켓 연결 엔드포인트: 클라이언트는 이 경로로 연결을 시도합니다.
        // SockJS는 웹소켓을 지원하지 않는 브라우저를 위해 대체 옵션을 제공합니다.
        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*") // 모든 도메인 허용 (운영 시 특정 도메인으로 제한 필요)
                .withSockJS();
    }
}