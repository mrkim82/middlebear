package com.groo.bear.pro.service.task;

import lombok.Data;

@Data
public class ProWorkViewVO {
	private String workName;
	private String workStatus;
	private String workPri;
	private String workManager;
	private String workStartDay;
	private String workEndDay;
	private String workDate;
	private String workNum;
	private String workWriter;
	private String workUpdateDay;
	
	private int proNo;
	private String id;
}
