package com.groo.bear.chat.controller;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.chat.domain.ChatMessageDTO;
import com.groo.bear.chat.domain.RoomDTO;
import com.groo.bear.chat.service.ChatService;

@Controller
//이 클래스는 STOMP 메시지를 받고 보내는 역할을 한다.
public class ChatController {
//웹소켓을 통해 실시간 채팅을 구현하는 로직을 수행
	
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    //SimpMessagingTemplate -> 메세지를 STOMP브로커로 전송하는데 사용
    
    @Autowired
    private ChatService chatService;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @GetMapping("/roomList")
    public String roomList(HttpSession session, Model model) {
    	String id = (String)session.getAttribute("Id");
    	model.addAttribute("userId", chatService.chatRoomList(id));
    	return "chat/rooms";
    }
    
    //채팅방을 전체 반복문으로 화면에 뽑아주기.
    @GetMapping("/roomGetList")
    @ResponseBody
    public List<RoomDTO> roomGetList(HttpSession session, Model model) {
        String id = (String)session.getAttribute("Id");
        List<RoomDTO> roomList = chatService.chatRoomList(id);
        model.addAttribute("userId", roomList);
        return roomList;
    }
    
    @PostMapping("/newChatroom")
    @ResponseBody
    public Map<String, Object> createChatroom(HttpSession session, @RequestBody RoomDTO room) {
    	String id = (String)session.getAttribute("Id");
    	room.setUserId(id);
        int createdRoom = chatService.createChatRoom(room);
        messagingTemplate.convertAndSend("/topic/chatrooms/", createdRoom);
        return Collections.singletonMap("roomNo", createdRoom);
    }
    //HTTP를 사용하려면 @PostMapping을, 웹소켓과 STOMP를 사용하려면 @MessageMapping
    
    @MessageMapping("/deleteChatroom")
    public void deleteChatroom(RoomDTO roomDTO) {
    	chatService.deleteChatRoom(roomDTO.getRoomNo());
    	messagingTemplate.convertAndSend("/topic/chatroomDeleted");
    }
    
    
}
