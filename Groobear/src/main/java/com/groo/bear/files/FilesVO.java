package com.groo.bear.files;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FilesVO {
//	FILE_NO          NOT NULL NUMBER(5)    
//	ORIGIN_FILE_NAME NOT NULL VARCHAR2(50) 
//	CHANGE_FILE_NAME NOT NULL VARCHAR2(50) 
//	FILE_SIZE        NOT NULL NUMBER(10)   
//	FILE_DATE        NOT NULL DATE         
//	MAIL_NO                   NUMBER(5)    
//	BOARD_NO                  NUMBER(5)    
//	PRO_FILE_NO               NUMBER(5)
	
	public int fileNo;
	public String originFileName;
	public String changeFileName;
	public BigDecimal fileSize;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date fileDate;
	public int mailNo;
	public int boardNo;
	public int proFileNo;
}
