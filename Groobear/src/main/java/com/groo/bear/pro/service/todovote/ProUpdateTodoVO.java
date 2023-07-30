package com.groo.bear.pro.service.todovote;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProUpdateTodoVO {
	private String todoContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+9")
	private Date todoEnd;
	private String id;
	private int todoNo;
	private int proPostNo;
	
	private String postTitle;
	
}
