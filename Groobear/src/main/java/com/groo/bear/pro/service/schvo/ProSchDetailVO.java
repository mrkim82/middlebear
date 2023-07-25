package com.groo.bear.pro.service.schvo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProSchDetailVO {
	private int proPostNo;
	private String postTitle;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date postDate;
	private String id;
	private String name;
	private String profileImg;
	private int schNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date schStartTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date schEndTime;
	private String schPlace;
	private String schContent;
	
}
