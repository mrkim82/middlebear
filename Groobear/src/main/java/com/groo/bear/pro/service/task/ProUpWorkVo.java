package com.groo.bear.pro.service.task;

import java.util.List;

import lombok.Data;

@Data
public class ProUpWorkVo {
	private int proPostNo;
	private String postTitle;
	
	//내용 수정
	private String workContent;
	
	//담당자 변경
	private List<String> ids;
}
