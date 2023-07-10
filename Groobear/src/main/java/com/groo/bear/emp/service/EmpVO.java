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
	private String id;
	private String password;
	private String email;
	private int phone;
	private String addr;
	private String empGrade;
	private Date empDate;
	private String sign;
	private String profileImg;
	private String empStatus;
	private String profileNote;
}
