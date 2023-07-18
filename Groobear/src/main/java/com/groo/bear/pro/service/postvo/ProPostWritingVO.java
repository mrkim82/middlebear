package com.groo.bear.pro.service.postvo;

import java.util.Date;

import lombok.Data;

@Data
public class ProPostWritingVO {
	private int proNo;
	private String id;
	private String postTitle;
	private Date postDate;
	private int postTopSeq;
	private String writingContent;
}
