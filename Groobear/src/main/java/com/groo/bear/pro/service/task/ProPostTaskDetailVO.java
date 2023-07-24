package com.groo.bear.pro.service.task;

import java.util.Date;

import lombok.Data;

@Data
public class ProPostTaskDetailVO {
	private int proPostNo;
	private String postTitle;
	private String id;
	private String name;
	private int workNo;
	private String workStatus;
	private String workPri;
	private int lrWorkSeq;
	private int workGroupNo;
	private String workContent;
	private String workGroupName;
	private String profileImg;
	
	private Date postDate;
	
	private Date workStartDay;
	private Date workStartTime;
	private Date workEndDay;
	private Date workEndTime;
	
	private Date workUpdateDay;
}
