package com.groo.bear.pro.service.postvo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProPostFeedVO {
	private int proPostNo;
	private String postType;
	private String postTitle;
	private String id;
    private int writingNo;
    private String writingContent;
    private int workNo;
    private String workStatus;
    private String workPri;
    private int lrWorkSeq;
    private String workContent;
    private int schNo;
    private String schPlace;
    private String schContent;
    private int voteNo;
    private String voteContent;
    private String voteEndCheck;
    private int proNo;
    
    private String postDate;
    private Date workStartDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date workStartTime;
    private String workEndDay;
    private String workEndTime;
    private String schStartDay;
    private String schStartTime;
    private String schEndDay;
    private String schEndTime;
    private String voteEndDay;
    
    private int workGroupNo;
    
    private String name;
    private String profileImg;
}
