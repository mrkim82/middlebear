package com.groo.bear.pro.service.postvo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProPostWorkVO {
	private int proNo;
	private String id;
	private String postTitle;
	private int workGroupNo;
	private String workStatus;
	private String workPri;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")//둘 다 있어야함
	private Date workStartDay;
	@DateTimeFormat(pattern = "HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date workStartTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date workEndDay;
	@DateTimeFormat(pattern = "HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date workEndTime;
	private String workContent;
	private int workNo;
	private String[] workPersonArr;
}
