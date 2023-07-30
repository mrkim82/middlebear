package com.groo.bear.pro.service.todovote;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProUpdateVoteVO {
	private String postTitle;
	private int proPostNo;
	private String voteContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date voteEndDay;
	
	//voteDetail 삽입용
	private List<String> voteDetailContents;
	//private String voteDetailImg;
	private int voteNo;
	
}
