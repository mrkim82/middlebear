package com.groo.bear.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
//	BOARD_NO	NUMBER(4,0)
//	ID	VARCHAR2(30 BYTE)
//	BOARD_TYPE	VARCHAR2(2 BYTE)
//	TITLE	VARCHAR2(100 BYTE)
//	CONTENT	VARCHAR2(3000 BYTE)
//	INSERT_DATE	DATE
//	UPDATE_DATE	DATE
//	COUNT	NUMBER(3,0)
	
	private int boardNo;
	private String id;
	private String name;
	private String boardType;
	private String title;
	private String content;
	@DateTimeFormat(pattern="yyyy.MM.dd")
	private Date insertDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateDate;
	private int count;
	
}
