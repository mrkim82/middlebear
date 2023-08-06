package com.groo.bear.pro.service.provo;

import lombok.Data;

@Data
public class ProAuthVO {
	private String proDefTab;
	private String postWriteAuth;
	private String postUpdateAuth;
	private String postViewAuth;
	private String comWriteAuth;
	private String fileAuth;
	private String id;
}
