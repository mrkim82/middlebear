package com.groo.bear.pro.service.todovote;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CreateVoteVO {
	private int proNo;
	private String id;
	private String postTitle;
	//private int voteNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date voteEndDay;
	private String voteContent;
	
	private List<CreateVoteSubVO> voteDetail;
	
}
