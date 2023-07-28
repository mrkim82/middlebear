package com.groo.bear.chat.controller;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    //하나의 대화방 메세지 보기
    @GetMapping("/chat/{roomNo}")
    public String rooms(HttpSession session, @PathVariable Integer roomNo, Model model, ChatMessageDTO chatDTO) {
    	String name = (String)session.getAttribute("Name");
    	model.addAttribute("name", name);
    	String id = (String)session.getAttribute("Id");
    	model.addAttribute("id", id);
    	model.addAttribute("roomNo", roomNo);
    	model.addAttribute("chatDTO", chatService.getMessagesForRoom(roomNo));
    	//나중에 서비스단도 추가될 예정
    	return "chat/chat";
    }
    
    @MessageMapping("/chat/{roomNo}")
    public void send(ChatMessageDTO chatMessage, @DestinationVariable int roomNo) {
        try {
            messagingTemplate.convertAndSend("/topic/messages/" + roomNo , chatMessage);
            chatService.sendMessage(chatMessage); //메세지를 데이터베이스에 저장
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @GetMapping("/roomList")
    public String roomList(HttpSession session, Model model) {
    	String id = (String)session.getAttribute("Id");
    	model.addAttribute("id", chatService.chatRoomList(id));
    	return "chat/rooms";
    }
    
    @PostMapping("/newChatroom")
    @ResponseBody
    public Map<String, Object> createChatroom(HttpSession session, @RequestBody RoomDTO room) {
    	String id = (String)session.getAttribute("Id");
    	room.setId(id);
        int createdRoom = chatService.createChatRoom(room);
        messagingTemplate.convertAndSend("/topic/chatrooms/", createdRoom);
        return Collections.singletonMap("roomNo", createdRoom);
    }
    //HTTP를 사용하려면 @PostMapping을, 웹소켓과 STOMP를 사용하려면 @MessageMapping
    
    @PostMapping("/deleteChatroom")
    @ResponseBody
    public ResponseEntity<?> deleteChatroom(@RequestBody RoomDTO room) {
        int isDeleted = chatService.deleteChatRoom(room); // chatService는 채팅방을 관리하는 서비스 객체입니다.

        if (isDeleted == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/empAllList")
    @ResponseBody
    public List<RoomDTO> empAllList() {
        return chatService.empAllList();
    }
}
