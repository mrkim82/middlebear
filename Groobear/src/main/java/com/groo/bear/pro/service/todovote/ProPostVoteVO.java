package com.groo.bear.pro.service.todovote;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProPostVoteVO {
	private int proPostNo;
	private int voteNo;
	private String voteContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date voteEndDay;
	private String voteEndCheck;
	
	private int voteDetailNo;
	private String voteDetailContent;
	private String voteDetailImg;
	
	private int votePartiNo;
	private String id;
	
	private int proNo;
	
}
