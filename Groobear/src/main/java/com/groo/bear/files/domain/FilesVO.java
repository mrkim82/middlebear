package com.groo.bear.files.domain;

import lombok.Data;

@Data
public class FilesVO {
	
//	FILE_NO          NOT NULL NUMBER(5)    
//	ORIGIN_FILE_NAME NOT NULL VARCHAR2(50) 
//	CHANGE_FILE_NAME NOT NULL VARCHAR2(50) 
//	FILE_SIZE        NOT NULL NUMBER(10)   
//	FILE_DATE        NOT NULL DATE         
//	MAIL_NO                   NUMBER(5)    
//	BOARD_NO                  NUMBER(5)    
//	PRO_FILE_NO               NUMBER(5)
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean image;
	
	private int boardNo;
	private int mailNo;
	private int proFileNo;
}
