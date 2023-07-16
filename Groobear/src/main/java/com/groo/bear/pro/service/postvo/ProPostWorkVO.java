package com.groo.bear.pro.service.postvo;

import java.util.List;

import lombok.Data;

@Data
public class ProPostWorkVO {
	private int proNo;
	private String id;
	private String postTitle;
	private int workGroupNo;
	private String workStatus;
	private String workPri;
	private String workStartDay;
	private String workStartTime;
	private String workEndDay;
	private String workEndTime;
	private String workContent;
	private List<String> workPersonArr;
}
