package com.groo.bear.mail.service;

import java.util.Date;

import lombok.Data;

@Data
public class MailVO {
	private Date sendDate;
	private Date receiveDate;
	private Date deleteDate; 
	private String readCheck;
	private String mailType;
	private String id;
	private String title;
	private String content;
	private String sender;
	private String receiver;
	private String referrer;
}
