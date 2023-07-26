package com.groo.bear.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groo.bear.chat.domain.ChatMessageDTO;
import com.groo.bear.chat.domain.MsgRoomMemDTO;

@Controller
//이 클래스는 STOMP 메시지를 받고 보내는 역할을 한다.
public class ChatController {
//웹소켓을 통해 실시간 채팅을 구현하는 로직을 수행
	
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    //SimpMessagingTemplate -> 메세지를 STOMP브로커로 전송하는데 사용

    @GetMapping("/chatDetail")
    public String rooms(Integer roomNo, Model model, ChatMessageDTO chatDTO) {
    	model.addAttribute("roomNo", roomNo);
    	//model.addAttribute("chatDTO", )
    	//나중에 서비스단도 추가될 예정
    	return "chat/chat";
    }
    
    @MessageMapping("/chat/{roomNo}")
    // 이 어노테이션은 "/chat"로 시작하는 STOMP 메시지를 처리하도록 설정. 이 메소드는 클라이언트에서 서버로 메시지를 보낼 때 호출
    public void send(ChatMessageDTO chatMessage, @DestinationVariable int roomNo) {
    	//이 메소드는 클라이언트로부터 받은 메세지를 STOMP브로커로 전송한다.
        try {
            messagingTemplate.convertAndSend("/topic/messages/" + roomNo , chatMessage);
            //messagingTemplate.convertAndSend이 부분이 @SendTo 역할을 대체한다.
            //SimpMessagingTemplate의 'converAndSend'메소드는 메세지를 해당 주소에 전송한다.
            //@SendTo 어노테이션은 메소드의 반환값을 전달하는 데 사용되므로
            //messagingTemplate.convertAndSend 메소드를 사용하면 반환값을 따로 전달할 필요가 없다.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
