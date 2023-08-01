package com.groo.bear.pro.service;

import lombok.Data;

@Data
public class ProFileVO {
	private String uuid; // 첨부파일 보관
	private String uploadPath; //파일이 업로드된 경로
	private String fileName; //파일이름
	private boolean fileType; // 이미지 파일 여부
	
	private int proFileNo;
	
	private int proPostNo;//파일 관리용
	private int comNo;//파일 관리용
}
