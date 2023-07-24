package com.groo.bear.pro.service.schvo;

import java.util.Date;

import lombok.Data;

@Data
public class ProPostWorkSchVO {
	private int proPostNo;
	private String postType;
	private String postTitle;
	
	private int workNo;
	private String workStatus;
	private Date workStartDay;
	private Date workStartTime;
	private Date workEndDay;
	private Date workEndTime;
	
	private int schNo;
	private Date schStartDay;
	private Date schStartTime;
	private Date schEndDay;
	private Date schEndTime;
	
}
