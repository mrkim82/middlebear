package com.groo.bear.mypage.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OffVO {

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date useDay;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnDay;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private int offCount;
	private String id;
	
	
	private String offStatus;
	private String name;
	private int empNo;
	private String rank;
	private String empGrade;
	
	
}
