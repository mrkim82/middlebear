package com.groo.bear.chat.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker //WebSocket 서버를 활성화하는데 사용되는 데코레이터
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    //configureMessageBroker() -> 메시지 브로커 옵션을 설정
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app"); 
        // /app으로 시작하는 메세지는 애플리케이션은 메시지 핸들러(controller)로 라우팅
        // /topic 으로 시작하는 메세지는 브로커로 라우팅된다. 
        // 이 브로커는 해당 주제를 구독하는 모든 클라이언트에 메세지를 전송.
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
    //registerStompEndpoints 메소드는 클라이언트가 웹 소켓을 열 수 있는 엔드포인트를 등록
    

}







