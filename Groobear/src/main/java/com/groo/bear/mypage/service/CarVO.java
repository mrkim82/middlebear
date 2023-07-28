package com.groo.bear.mypage.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CarVO {

	private String carNo;
	private String oldCarNo;
	private String carType;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date carDate;
	private String id;
	private String deptName;
	private String phone;
	private String name;
	private int empNo;
	private String rank;
	private String empGrade;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date operDate;
	private String destination;
	private String befored;
	private String afterd;
	private int imprest;
	private String purpose;
	private String approver2;
	private String approver3;
	private String payStatus1;
	private String payStatus2;
	private String payStatus3;
	private int payNo;
	private String docType;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date issueDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDay;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDay;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+9")
	private Date day;

	
	
	
}
