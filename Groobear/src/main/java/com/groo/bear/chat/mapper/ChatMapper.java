package com.groo.bear.chat.mapper;

import java.util.List;

import com.groo.bear.chat.domain.ChatMessageDTO;
import com.groo.bear.chat.domain.RoomDTO;

public interface ChatMapper {
	
	//채팅방 전체 조회
	public List<RoomDTO> chatRoomList(String id);
	
	//채팅방 생성
	public int createChatRoom(RoomDTO roomDTO);
	
	//채팅방 삭제
	public int deleteChatRoom(RoomDTO roomDTO);
	
	//메세지 전체조회
	public List<ChatMessageDTO> MessageAllList(String id);
	
	//메세지 등록
	public int sendMessage(ChatMessageDTO msgDTO);
	
	//하나의 대화방 메세지보기 에서 값 넘겨주려고
	public List<ChatMessageDTO> getMessagesForRoom(int roomNo);
	
	//사원 전체조회 -> 모달에서 띄우기 위함
	public List<RoomDTO> empAllList();
	
}
