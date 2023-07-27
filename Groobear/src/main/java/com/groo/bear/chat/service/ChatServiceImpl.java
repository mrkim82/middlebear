package com.groo.bear.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.chat.domain.ChatMessageDTO;
import com.groo.bear.chat.domain.RoomDTO;
import com.groo.bear.chat.mapper.ChatMapper;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	ChatMapper chatMapper;
	
	@Override
	public List<RoomDTO> chatRoomList(String userId) {
		return chatMapper.chatRoomList(userId);
	}

	@Override
	public List<ChatMessageDTO> MessageAllList() {
		return chatMapper.MessageAllList();
	}
	
	@Override
	public int createChatRoom(RoomDTO roomDTO) {
		return chatMapper.createChatRoom(roomDTO);
	}

	@Override
	public int deleteChatRoom(RoomDTO roomDTO) {
		return chatMapper.deleteChatRoom(roomDTO);
	}
	
}
