package com.groo.bear.pro.service.postvo;

import lombok.Data;

@Data
public class ProPostUserVO {
	private String name;
	private int proNo;
	private int empNo;
	private String id;
	private String profileImg;
	private String rank;
	private String deptName;
	private String phone;
	private String email;
	private String profileNote;
	
	private String uuid;
	private String uploadPath;
	private String fileName;
}
