package com.groo.bear.chat.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatMessageDTO {
	//final을 사용한 이유
	//어차피 메세지를 보낼 때 마다 객체를 생성해야 하기 때문에
	// 1. 안전 -> 데이터 일관 유지 가능 , 2. 예측 가능 3. 재사용
	private final int msgNo;
    private final int roomNo;
    private final String userId;
    private final String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Date msgTime;
    
    //생성자 만드는 이유
    //인스턴스 생성시 필드에 초기값으로 부여하기 위함.
    public ChatMessageDTO(int msgNo, int roomNo, String userId, String content, Date msgTime) {
        this.msgNo = msgNo;
        this.roomNo = roomNo;
        this.userId = userId;
        this.content = content;
        this.msgTime = msgTime;
    }
}
