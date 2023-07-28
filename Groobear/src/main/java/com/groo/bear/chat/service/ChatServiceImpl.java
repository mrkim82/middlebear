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
	public List<ChatMessageDTO> MessageAllList(int roomNo) {
		return chatMapper.MessageAllList(roomNo);
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
	public List<RoomDTO> empAllList() {
		return chatMapper.empAllList();
	}
}
