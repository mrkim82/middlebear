package com.groo.bear.pro.service.todovote;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CreateVoteVO {
	private String postTitle;
	private String voteContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date voteEndDay;
	
	private String voteBody1;
	private String voteBody2;
	private String voteBody3;
	private String voteBody4;
	private String voteBody5;
	private String voteBody6;
	private String voteBody7;
	private String voteBody8;
	private String voteBody9;
	private String voteBody10;
	
	private int proNo;
	private String id;
	
}
