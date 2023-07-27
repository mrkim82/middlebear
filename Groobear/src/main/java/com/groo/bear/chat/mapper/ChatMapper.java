package com.groo.bear.chat.mapper;

import java.util.List;

import com.groo.bear.chat.domain.ChatMessageDTO;
import com.groo.bear.chat.domain.RoomDTO;

public interface ChatMapper {
	
	//채팅방 전체 조회
	public List<RoomDTO> chatRoomList(String userId);
	
	//채팅방 생성
	public int createChatRoom(RoomDTO roomDTO);
	
	//채팅방 삭제
	public int deleteChatRoom(RoomDTO roomDTO);
	
	//메세지 전체조회
	public List<ChatMessageDTO> MessageAllList();
}
