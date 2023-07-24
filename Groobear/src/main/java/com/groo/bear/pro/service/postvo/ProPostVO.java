package com.groo.bear.pro.service.postvo;

import lombok.Data;

@Data
public class ProPostVO {
	//pro
	private int proNo;
	private String proName;
	private String proContent;
	private String postWriteAuth;
	private String postUpdateAuth;
	private String postViewAuth;
	private String comWriteAuth;
	private String fileAuth;
	private String id;
	
	private int proMemNo;
	private String proColor;
	private String proStarCheck;
	private String homeTab;
	private String workName;
	private String workStatus;
	private String workPri;
	private String workManager;
	private String workStartDay;
	private String workEndDay;
	private String workDate;
	private String workNum;
	private String workWrite;
	private String workUpdate;
	
	private int count;
	
}
