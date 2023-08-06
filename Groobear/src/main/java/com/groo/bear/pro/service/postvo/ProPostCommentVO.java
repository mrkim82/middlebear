package com.groo.bear.pro.service.postvo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//import java.util.Date;

//import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProPostCommentVO {
	private int comNo;
	private String comContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date comDate;
	private String name;
	private int proPostNo;
	private String id;
	
	private String uuid;
	private String uploadPath;
	private String fileName;
}
