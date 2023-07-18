package com.groo.bear.board.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.groo.bear.files.domain.FilesVO;

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
	
	private List<FilesVO> attchList;
	
	
//	COM_NO      NOT NULL NUMBER(5)     
//	PRO_POST_NO          NUMBER(5)     
//	COM_CONTENT          VARCHAR2(300) 
//	COM_DATE    NOT NULL DATE          
//	BOARD_NO             NUMBER(5)     
//	ID                   VARCHAR2(30)
	private int comNo;
	private String profileImg;
	private String comContent;
	private String comDate;
}
