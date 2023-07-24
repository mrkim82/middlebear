package com.groo.bear.chat;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatMessageDTO {
	//메세지방에서 필요한 정보
	private int msgNo;
	private int roomNo;
	private String id; 
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date msgTime;
	private int msgCheck;
	
	private String roomName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date roomTime;
	
	//생성자
	//생성자 만들 때 필요한거 msgNo, roomNo, id, content, msgTime, msgCheck
	//
}
