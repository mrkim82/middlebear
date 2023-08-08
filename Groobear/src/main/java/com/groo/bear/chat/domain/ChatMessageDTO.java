package com.groo.bear.chat.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChatMessageDTO {
	private int msgNo;
    private Integer roomNo;
    private String id;
    private String content;
    private String name;
    //@JsonFormat(pattern="yy-MM-dd HH:mm", timezone = "GMT+9")
    //@DateTimeFormat(pattern = "yy-MM-dd HH-mm")
    private String msgTime;
    private String created;
    private String profileImageUrl;
    private String uuid; // 첨부파일 보관
	private String uploadPath; //파일이 업로드된 경로
	private String fileName; //파일이름
	private boolean fileType; // 이미지 파일 여부
    public ChatMessageDTO() {}
    
    //생성자 만드는 이유
    //인스턴스 생성시 필드에 초기값으로 부여하기 위함.
    public ChatMessageDTO(int msgNo, int roomNo, String id, String content, String  msgTime, String name) {
        this.msgNo = msgNo;
        this.roomNo = roomNo;
        this.id = id;
        this.content = content;
        this.msgTime = msgTime;
        this.name = name;
    }
}
