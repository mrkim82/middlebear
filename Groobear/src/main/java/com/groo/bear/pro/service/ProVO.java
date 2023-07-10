package com.groo.bear.pro.service;

import lombok.Data;

@Data
public class ProVO {
	private int proNo;
	private String proName;
	private String proContent;
	private String proDefTab;
	private String postWriteAuth;
	private String postUpdateAuth;
	private String postViewAuth;
	private String comWriteAuth;
	private String fileAuth;
	private String id;
}
