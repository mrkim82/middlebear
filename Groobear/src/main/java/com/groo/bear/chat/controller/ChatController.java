package com.groo.bear.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.groo.bear.chat.domain.ChatMessageDTO;

@Controller
//이 클래스는 STOMP 메시지를 받고 보내는 역할을 한다.
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    //@MessageMapping 어노테이션은 어떤 메시지를 핸들링 할 것인지 정의하며,
    public void send(ChatMessageDTO chatMessage) throws Exception {
        messagingTemplate.convertAndSend("/topic/messages", chatMessage);
    }
}
