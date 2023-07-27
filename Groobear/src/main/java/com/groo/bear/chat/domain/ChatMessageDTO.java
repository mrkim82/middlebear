package com.groo.bear.chat.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatMessageDTO {
	private int msgNo;
    private Integer roomNo;
    private String userId;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate msgTime;
    
    public ChatMessageDTO() {}
    
    //생성자 만드는 이유
    //인스턴스 생성시 필드에 초기값으로 부여하기 위함.
    public ChatMessageDTO(int msgNo, int roomNo, String userId, String content, LocalDate msgTime) {
        this.msgNo = msgNo;
        this.roomNo = roomNo;
        this.userId = userId;
        this.content = content;
        this.msgTime = msgTime;
    }
}
