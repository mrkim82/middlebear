package com.groo.bear.payment.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.groo.bear.files.domain.FilesVO;

import lombok.Data;

@Data
public class PaymentVO {
	//전자문서 공용 data
	private int payNo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	private String docType;
	private String id;
	private String approver2;
	private String approver3;
	private String referrer;
	private String payStatus1;
	private String payStatus2;
	private String payStatus3;
	private String publicSign;
	//운행일지용 data
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
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
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date useDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;
	
	private List<FilesVO> attachList;
	
	private int comNo;
	private String profileImg;
	private String comContent;
	private String comDate;
	
}
