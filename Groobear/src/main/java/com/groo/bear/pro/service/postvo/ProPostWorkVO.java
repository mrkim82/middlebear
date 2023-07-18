package com.groo.bear.pro.service.postvo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProPostWorkVO {
	private int proNo;
	private String id;
	private String postTitle;
	private int workGroupNo;
	private String workStatus;
	private String workPri;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date workStartDay;
	@DateTimeFormat(pattern = "HH:mm")
	private Date workStartTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date workEndDay;
	@DateTimeFormat(pattern = "HH:mm")
	private Date workEndTime;
	private String workContent;
	private List<Object> workPersonArr;
}
