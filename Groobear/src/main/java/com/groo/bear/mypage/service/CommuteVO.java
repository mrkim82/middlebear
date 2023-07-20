package com.groo.bear.mypage.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CommuteVO {

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date day;
	@DateTimeFormat(pattern="HH24-MI")
	private Date workStart;
	@DateTimeFormat(pattern="HH24-MI")
	private Date workEnd;
	private String workTime;
	@DateTimeFormat(pattern="HH24-MI")
	private Date overWorkStart;
	@DateTimeFormat(pattern="HH24-MI")
	private Date overWorkEnd;
	
	private int overWorkTime;
	private String offStatus;
	private String id;
	private String offCount;
	
	private String deptName;
	private String name;
	private int empNo;
	private String rank;
	private String empGrade;
	
	
}
