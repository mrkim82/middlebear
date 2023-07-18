package com.groo.bear.com.service;

import java.util.Date;

import lombok.Data;

@Data
public class ComVO {
//	COM_NO	NUMBER(5,0)
//	PRO_POST_NO	NUMBER(5,0)
//	COM_CONTENT	VARCHAR2(300 BYTE)
//	COM_DATE	DATE
//	BOARD_NO	NUMBER(5,0)
//	ID	VARCHAR2(30 BYTE)
	private int comNo;
	private int proPostNo;
	private String comContent;
	private Date comDate;
	private int boardNo;
	private String id;
}
