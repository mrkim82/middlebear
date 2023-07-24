package com.groo.bear.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDTO {
//채팅방에 대한 정보를 저장
	
    private String roomId; //채팅방의 고유 ID. UUID 를 이용하여 생성
    private String name; //채팅방의 이름
    private Set<WebSocketSession> sessions = new HashSet<>();
    //채팅방에 연결된 웹소켓 세션의 집합. 한 웹소켓 세션은 한 클라이언트의 연결을 의미
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

    public static ChatRoomDTO create(String name){
    	//채팅방의 이름을 인자로 받아 새로운 'ChatRoomDTO' 인스턴스를 생성하고, 이 채팅방에 고유한 
    	//ID를 부여한 후, 이 채팅방 인스턴스를 반환한다. 이 메소드는 새로운 채팅방을 생성할 때 사용된다.
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }
}
