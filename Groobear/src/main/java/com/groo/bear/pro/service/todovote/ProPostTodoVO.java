package com.groo.bear.pro.service.todovote;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProPostTodoVO {
	private int todoNo;
	private int proPostNo;
	private String todoContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date todoEnd;
	private String todoCheck;
	private String id;
	private String name;
	
	private int proNo;
	
	
}
