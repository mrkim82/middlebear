package com.groo.bear.pro.service.postvo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProDetailSearchVO {
	private int proNo;
	private int proPostNo;
	private String postType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date postDate;
	private String postTitle;
	private String id;
    private String writingContent;
    private String schContent;
    private String voteContent;
    private String workContent;
    private String todoContent;
    
    private String name;
    private String search;
}
