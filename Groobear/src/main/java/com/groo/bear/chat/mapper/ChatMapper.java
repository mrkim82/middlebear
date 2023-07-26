package com.groo.bear.chat.mapper;

import java.util.List;

import com.groo.bear.chat.domain.ChatMessageDTO;

public interface ChatMapper {
	
	//메세지 전체조회
	public List<ChatMessageDTO> MessageAllList();
}
