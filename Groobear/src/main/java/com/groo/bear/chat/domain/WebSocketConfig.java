package com.groo.bear.chat.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration // 이 클래스가 Spring IoC Container에 의해 빈으로 등록될 설정 클래스임을 나타냄
@EnableWebSocketMessageBroker //WebSocket 서버를 활성화하는데 사용되는 데코레이터
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//클라이언트와 서버 간의 실시간 메세징 기능을 위한 WebSocket과 STOMP 설정을 담당하는 클래스

    @Override
    //configureMessageBroker() -> 메시지 브로커 옵션을 설정
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app"); 
        
        // 클라이언트는 "/app"으로 시작하는 주소로 메세지를 보낼 수 있고,
        // 서버는 "/topic" 으로 시작하는 주소를 구독하는 클라이언트에게 메세지를 전송할 수 있다.
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chatserver");
    }
    //registerStompEndpoints 메소드는 클라이언트가 웹 소켓을 열 수 있는 엔드포인트를 등록
}

