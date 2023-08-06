package com.groo.bear.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.chat.domain.ChatMessageDTO;
import com.groo.bear.chat.domain.RoomDTO;
import com.groo.bear.chat.mapper.ChatMapper;
import com.groo.bear.files.domain.FilesVO;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	ChatMapper chatMapper;
	
	@Override
	public List<RoomDTO> chatRoomList(String id) {
		return chatMapper.chatRoomList(id);
	}

	
	@Override
	public int createChatRoom(RoomDTO roomDTO) {
	    return chatMapper.createChatRoom(roomDTO);
	}

	@Override
	public int deleteChatRoom(RoomDTO roomDTO) {
		return chatMapper.deleteChatRoom(roomDTO);
	}

	@Override
	public List<ChatMessageDTO> MessageAllList(ChatMessageDTO chatMessageDTO) {
		return chatMapper.MessageAllList(chatMessageDTO);
	}

	@Override
	public int sendMessage(ChatMessageDTO msgDTO) {
		return chatMapper.sendMessage(msgDTO);
	}

	@Override
	public List<ChatMessageDTO> getMessagesForRoom(int roomNo) {
		return chatMapper.getMessagesForRoom(roomNo);
	}

	@Override
	public List<RoomDTO> empAllList(String id) {
		return chatMapper.empAllList(id);
	}

	@Override
	public int newJeans() {
		return chatMapper.newJeans();
	}


	@Override
	public int insertMem(RoomDTO roomDTO) {
	    return chatMapper.insertMem(roomDTO);
	}
	
	//빈방 삭제
	@Override
	public void deleteEmptyRooms() {
	        List<Integer> emptyRooms = chatMapper.findEmptyRooms();
	        for (Integer roomNo : emptyRooms) {
	        	chatMapper.deleteRoom(roomNo);
        }
    }


	@Override
	public List<RoomDTO> empListExcludingRoomMembers(String id, int roomNo) {
	    return chatMapper.empListExcludingRoomMembers(id, roomNo);
	}


	@Override
	public int countRoomMembers(int roomNo) {
		return chatMapper.countRoomMem(roomNo);
	}


	@Override
	public RoomDTO getLastMessage(int roomNo) {
	    return chatMapper.getLastMessage(roomNo);
	}


	@Override
	public List<ChatMessageDTO> MessageAllListReal(ChatMessageDTO chatMessageDTO) {
		return chatMapper.MessageAllListReal(chatMessageDTO);
	}


	@Override
	public RoomDTO getRoomName(int roomNo) {
		return chatMapper.getRoomName(roomNo);
	}


	@Override
	public List<RoomDTO> getUsersName(int roomNo) {
		return chatMapper.getUsersName(roomNo);
	}


	@Override
	public List<FilesVO> getImage() {
		return chatMapper.getImage();
	}
	
	
	
}
