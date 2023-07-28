package com.groo.bear.chat.service;

import java.util.List;

import com.groo.bear.chat.domain.ChatMessageDTO;
import com.groo.bear.chat.domain.RoomDTO;

public interface ChatService {
	
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
	
	//대화방에서 메세지 순서 맞춰주기
	public List<ChatMessageDTO> getMessagesForRoom(int roomNo);
	
	//사원 전체조회 -> 모달에서 띄우기 위함
	public List<RoomDTO> empAllList();
}
