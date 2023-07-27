package com.groo.bear.mypage.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommuteVO {

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+9")
	private Date day;
	@DateTimeFormat(pattern="HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+9")
	private Date workStart;
	@DateTimeFormat(pattern="HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+9")
	private Date workEnd;
	
	private String workTime;
	
	@DateTimeFormat(pattern="HH24-MI")
	private Date overWorkStart;
	@DateTimeFormat(pattern="HH24-MI")
	private Date overWorkEnd;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDay;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDay;
	
	private String monthDate;
	
	private int overWorkTime;
	private String offStatus;
	private String id;
	private String offCount;
	
	private String deptName;
	private String name;
	private int empNo;
	private String rank;
	private String empGrade;
	
	private String count;
	private String over;
	
	private String workStart2;
	private String workEnd2;
	private String day2;
	
	
}
