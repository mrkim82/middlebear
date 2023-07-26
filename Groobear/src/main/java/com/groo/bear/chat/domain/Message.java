package com.groo.bear.chat.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "message")
@Data
public class Message {
	
	@Id // -> 직접할당. 엔티티의 기본키 필드에 값을 직접 넣어 등록한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	//기본키 생성을 데이터베이스에 위임 -> 대신 만들어줌.
    private int msgNo;
	
	@ManyToOne
    @JoinColumn(name = "room_no", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Users user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "msg_time", nullable = false)
    @DateTimeFormat(pattern = "yy-MM-dd")
    private  LocalDate msgTime;
}
