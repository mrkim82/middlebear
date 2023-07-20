package com.groo.bear.pro.service.todovote;

import java.util.Date;

import lombok.Data;

@Data
public class ProPostTodoVO {
	private int todoNo;
	private int proPostNo;
	private String todoContent;
	private Date todoEnd;
	private String todoCheck;
	private String id;
	private String name;
	
	private int proNo;
	
}
