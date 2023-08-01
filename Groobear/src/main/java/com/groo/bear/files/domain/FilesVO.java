package com.groo.bear.files.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FilesVO {
	
	private String uuid; // 첨부파일 보관
	private String uploadPath; //파일이 업로드된 경로
	private String fileName; //파일이름
	private boolean fileType; // 이미지 파일 여부
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+9")
	private Date fileDate; //file_date default sysdate
	private int boardNo; 
	private int mailNo;
	private int proFileNo;
	private int payNo;
	private String name;
	private int no;
	private String id;
	private int signNo;
	
}
