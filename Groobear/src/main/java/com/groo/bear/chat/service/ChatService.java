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
	public List<ChatMessageDTO> MessageAllList(ChatMessageDTO chatMessageDTO);
	
	//메세지 등록
	public int sendMessage(ChatMessageDTO msgDTO);
	
	//대화방에서 메세지 순서 맞춰주기
	public List<ChatMessageDTO> getMessagesForRoom(int roomNo);
	
	//사원 전체조회 -> 모달에서 띄우기 위함
	public List<RoomDTO> empAllList(String id);
	
	//방번호 최신거 뽑아주기
	public int newJeans();
	
	//방에 들어갈 멤버 추가
	public int insertMem(RoomDTO roomDTO);
	
	//채팅방 삭제
	public void deleteEmptyRooms();
	
	//초대된 사람 제외하고 초대하기
	public List<RoomDTO> empListExcludingRoomMembers(String id, int roomNo);
	
	//방 인원 표시
	public int countRoomMembers(int roomNo);
	
	//가장 최신 내용 가져오기
	public RoomDTO getLastMessage(int roomNo);
	
	//MessageAllListReal
	public List<ChatMessageDTO> MessageAllListReal(ChatMessageDTO chatMessageDTO);
	
	//방 이름 확인용
	public RoomDTO getRoomName(int roomNo);
	
	//방에 있는 이름
	public List<RoomDTO> getUsersName(int roomNo);
}
