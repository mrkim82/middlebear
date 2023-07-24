package com.groo.bear.chat;

import com.groo.bear.chat.ChatRoomDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Stream;

@Repository
//Spring에서 데이터 저장소를 담당하는 클래스에 주로 사용되는 어노테이션입니다. 
//이 어노테이션이 붙어있으면, Spring은 이 클래스를 데이터 관련 Bean으로 관리하게 됩니다.
public class ChatRoomRepository {
	
	//  채팅방의 ID를 키로, ChatRoomDTO 객체를 값으로 저장하는 맵을 선언합니다.
	//  이 맵은 각 채팅방의 정보를 저장하게 됩니다.
    private Map<String, ChatRoomDTO> chatRoomDTOMap;

    //PostConstruct : 이 어노테이션은 메소드 위에 선언하면, 빈이 초기화된 후에 실행된다.
    //즉, 해당 클래스의 인스턴스가 생성된 후, 초기화를 위해'init()'메소드가 실행된다.
    //이 메소드는 chatRoomDTOMap을 LinkedHashMap으로 초기화 하고 있다.
    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    //
    public List<ChatRoomDTO> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDTO findRoomById(String id){
        return chatRoomDTOMap.get(id);
    }

    public ChatRoomDTO createChatRoomDTO(String name){
        ChatRoomDTO room = ChatRoomDTO.create(name);
        chatRoomDTOMap.put(room.getRoomId(), room);

        return room;
    }
}
