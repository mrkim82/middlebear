package com.groo.bear.car.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CarVO {

	private String carNo;
	private String carType;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date carDate;
	private String id;
	private String deptName;
	private String phone;
	private String name;
	private int empNo;
	private String rank;
	
}
