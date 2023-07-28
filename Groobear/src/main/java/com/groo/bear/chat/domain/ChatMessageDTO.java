package com.groo.bear.chat.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatMessageDTO {
	private int msgNo;
    private Integer roomNo;
    private String id;
    private String content;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate msgTime;
    
    public ChatMessageDTO() {}
    
    //생성자 만드는 이유
    //인스턴스 생성시 필드에 초기값으로 부여하기 위함.
    public ChatMessageDTO(int msgNo, int roomNo, String id, String content, LocalDate msgTime, String name) {
        this.msgNo = msgNo;
        this.roomNo = roomNo;
        this.id = id;
        this.content = content;
        this.msgTime = msgTime;
        this.name = name;
    }
}
