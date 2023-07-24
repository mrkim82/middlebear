package com.groo.bear.pro.service.todovote;

import java.util.Date;

import lombok.Data;

@Data
public class ProPostVoteVO {
	private int proPostNo;
	private int voteNo;
	private String voteContent;
	private Date voteEndDay;
	private String voteEndCheck;
	
	private int voteDetailNo;
	private String voteDetailContent;
	private String voteDetailImg;
	
	private int votePartiNo;
	private String id;
	
	private int proNo;
	
}
