package com.groo.bear.chat.controller;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
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
    
    //메세지 전체조회
    @GetMapping("/chat/{roomNo}")
    public String rooms(HttpSession session, @PathVariable Integer roomNo, Model model, ChatMessageDTO chatDTO) {
        String name = (String)session.getAttribute("Name");
        model.addAttribute("name", name);
        String id = (String)session.getAttribute("Id");
        model.addAttribute("id", id);
        model.addAttribute("roomNo", roomNo);
     // ChatMessageDTO로 변경하고 시간 정보를 포함한 메시지 전체 리스트를 가져옵니다.
        List<ChatMessageDTO> chatMessageList = chatService.MessageAllList(roomNo);
        model.addAttribute("chatDTO", chatMessageList);

        return "chat/chat";
    }
    //메세지 받고 주기
    @MessageMapping("/chat/{roomNo}") 
    public void send(ChatMessageDTO chatMessage, @DestinationVariable int roomNo) {
        try {
            messagingTemplate.convertAndSend("/topic/messages/" + roomNo , chatMessage);
            chatService.sendMessage(chatMessage); 
            System.out.println(chatMessage + "chatMessage 찾기용");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //채팅방 전체 리스트
    @GetMapping("/roomList")
    public String roomList(HttpSession session, Model model) {
    	//빈방삭제
    	chatService.deleteEmptyRooms();
    	
    	String id = (String)session.getAttribute("Id");
    	List<RoomDTO> rooms = chatService.chatRoomList(id);
        for (RoomDTO room : rooms) {
            int count = chatService.countRoomMembers(room.getRoomNo());
            room.setParticipantCount(count); // 참여자 수 설정
         // 마지막 메시지 설정
            String lastMessage = chatService.getLastMessage(room.getRoomNo());
            if (lastMessage != null) {
                room.setLastMessage(lastMessage);
            } else {
                room.setLastMessage("메세지 내용이 없습니다.");
            }
        }
        model.addAttribute("rooms", rooms);
    	
    	return "chat/rooms";
    }
    
    //채팅방 생성.
    @PostMapping("/newChatroom")
    @ResponseBody
    public Map<String, Object> createChatroom(HttpSession session, @RequestBody RoomDTO room) {
    	// HTTP 요청 본문의 JSON을 RoomDTO 객체로 변환.
    	//이 메서드의 반환값을 HTTP 응답 본문으로 변환하여 클라이언트에 전송
    	
    	String id = (String)session.getAttribute("Id");
    	room.setId(id);
    	
    	//직원ID 목록 가져오기
    	List<String> employeeIds = room.getEmployeeIds();
    	room.setParticipantCount(employeeIds.size() + 1); // 참여자 수 설정
    	
    	chatService.createChatRoom(room);
    	int roomNo = chatService.newJeans();
    	room.setRoomNo(roomNo);
        chatService.insertMem(room);
        for(String empId : employeeIds) {
            room.setId(empId);
            chatService.insertMem(room);
        }
        System.out.println(employeeIds);
        
        messagingTemplate.convertAndSend("/topic/chatrooms/", roomNo);
        return Collections.singletonMap("roomNo", roomNo);
    }
    

    
    //HTTP를 사용하려면 @PostMapping을, 웹소켓과 STOMP를 사용하려면 @MessageMapping
    
  //채팅방나가기.
    @PostMapping("/deleteChatroom")
    @ResponseBody
    public ResponseEntity<?> deleteChatroom(@RequestBody RoomDTO roomDTO) {
        String id = roomDTO.getId();
        int roomNo = roomDTO.getRoomNo();

        // 현재 시간을 가져와 원하는 문자열 형식으로 변환
        LocalDateTime now = LocalDateTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm"));

        // 사용자가 채팅방을 나갔음을 알리는 메시지 생성
        ChatMessageDTO chatMessage = new ChatMessageDTO();
        chatMessage.setContent(id + "님이 채팅방에서 나갔습니다. (" + formattedTime + ")");
        chatMessage.setRoomNo(roomNo);
        System.out.println(id + "님이 채팅방에서 나갔습니다. 테스트 (" + formattedTime + ")");
        // 메시지를 채팅방에 전송
        System.out.println(chatMessage);                                                                                                                                                         
        messagingTemplate.convertAndSend("/topic/messages/" + roomNo, chatMessage);
        // 메시지를 데이터베이스에 저장
        System.out.println(chatMessage);
        chatService.sendMessage(chatMessage);

        int isDeleted = chatService.deleteChatRoom(roomDTO); // chatService는 채팅방을 관리하는 서비스 객체입니다.
        if (isDeleted == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @GetMapping("/empAllList")
    @ResponseBody
    public List<RoomDTO> empAllList(HttpSession session) {
    	String id = (String) session.getAttribute("Id");
        return chatService.empAllList(id);
    }
    //메세지방 안에서 초대하기
    @PostMapping("/inviteEmployees")
    @ResponseBody
    public ResponseEntity<?> inviteEmployees(@RequestBody RoomDTO roomDTO) {
        List<String> employeeIds = roomDTO.getEmployeeIds();
        int roomNo = roomDTO.getRoomNo();
        
        for(String id : employeeIds) {
            RoomDTO room = new RoomDTO();
            room.setId(id);
            room.setRoomNo(roomNo);
            chatService.insertMem(room);
            // 현재 시간을 가져와 원하는 문자열 형식으로 변환
            LocalDateTime now = LocalDateTime.now();
            String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm"));

            // 새로운 사용자를 초대했음을 알리는 메시지 생성
            ChatMessageDTO chatMessage = new ChatMessageDTO();
            chatMessage.setContent(id + "님이 채팅방에 입장하였습니다. (" + formattedTime + ")");
            //chatMessage.setMsgTime(now);
            chatMessage.setRoomNo(roomNo);
            // 메시지를 채팅방에 전송
            messagingTemplate.convertAndSend("/topic/messages/" + roomNo, chatMessage);
            System.out.println();
            chatService.sendMessage(chatMessage);
        }

        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/empListExcludingRoomMembers/{roomNo}")
    @ResponseBody
    public List<RoomDTO> empListExcludingRoomMembers(HttpSession session, @PathVariable Integer roomNo) {
        String id = (String) session.getAttribute("Id");
        return chatService.empListExcludingRoomMembers(id, roomNo);
    }
    
}
