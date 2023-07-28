package com.groo.bear.emp.service;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	private int empNo;
	private String name;
	private String rank;
	private String pno;
	private Date pinfoDate;
	private int deptNo;
	private String deptName;
	private String deptHead;
	private Date deptDate; 
	private String deptAuth;
	private int deptCnt;
	private String id;
	private String password;
	private String email;
	private String phone;
	private String addr;
	private String empGrade;
	private Date empDate;
	private String sign;
	private String profileImg;
	private String empStatus;
	private String profileNote;
	private String proRange;
	private String proPartiFilter;
	private String uuid; // 첨부파일 보관
	private String uploadPath; //파일이 업로드된 경로
	private String fileName; //파일이름
	private boolean fileType; // 이미지 파일 여부
	private Date fileDate; //file_date default sysdate
	private String addrDetail;
	private String code;
	private String codeName;
	private String groupCode;
}
