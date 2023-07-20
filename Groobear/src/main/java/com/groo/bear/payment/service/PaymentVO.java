package com.groo.bear.payment.service;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentVO {
	//전자문서 공용 data
	private int payNo;
	private Date issueDate;
	private String docType;
	private String refferrer;
	private String approver2;
	private String approver3;
	private String payStatus1;
	private String payStatus2;
	private String payStatus3;
	private String publicSign;
	private String id;
	//운행일지용 data
	private Date operDate;
	private String destination;
	private int befored;
	private int afterd;
	private int imprest;
	private String purpose;
	private String carNo;
	//품의서용 data
	private String title;
	private String content;
	private String com;
	//연차계용 data
	private Date useDate;
	private Date returnDate;
}
