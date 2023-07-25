package com.groo.bear.pro.service.schvo;

import java.util.Date;

import lombok.Data;

@Data
public class ProSchDetailVO {
	private int proPostNo;
	private String postTitle;
	private Date postDate;
	private String id;
	private String name;
	private String profileImg;
	private int schNo;
	private Date schStartTime;
	private Date schEndTime;
	private String schPlace;
	private String schContent;
	
}
