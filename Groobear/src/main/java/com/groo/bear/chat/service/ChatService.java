package com.groo.bear.chat.service;

import java.util.List;

import com.groo.bear.chat.domain.ChatMessageDTO;

public interface ChatService {
	
	//메세지 전체조회
	public List<ChatMessageDTO> MessageAllList();
	
}
