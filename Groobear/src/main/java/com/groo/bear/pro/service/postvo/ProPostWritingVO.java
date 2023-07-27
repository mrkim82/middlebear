package com.groo.bear.pro.service.postvo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProPostWritingVO {
	private int proNo;
	private String id;
	private String postTitle;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date postDate;
	private int postTopSeq;
	private String writingContent;
	
	
	//글 조회(임시)
	private int proPostNo;
	private String postType;
	private int writingNo;
	private String name;
	private String profileImg;
	
}
