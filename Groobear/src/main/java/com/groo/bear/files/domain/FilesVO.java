package com.groo.bear.files.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FilesVO {
		
//	UUID        NOT NULL VARCHAR2(100) 
//	UPLOAD_PATH NOT NULL VARCHAR2(200) 
//	FILE_NAME   NOT NULL VARCHAR2(100) 
//	FILE_TYPE            CHAR(1)       
//	FILE_DATE            DATE          
//	BOARD_NO             NUMBER(5)     
//	MAIL_NO              NUMBER(5)     
//	PRO_FILE_NO          NUMBER(5)
	
	private String uuid; // 첨부파일 보관
	private String uploadPath; //파일이 업로드된 경로
	private String fileName; //파일이름
	private boolean fileType; // 이미지 파일 여부
	private Date fileDate; //file_date default sysdate
	private int boardNo; 
	private int mailNo;
	private int proFileNo;

	private String name;
	private int no;
	private String id;

	
}
