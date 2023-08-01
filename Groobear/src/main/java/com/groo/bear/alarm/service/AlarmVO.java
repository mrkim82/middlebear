package com.groo.bear.alarm.service;

import lombok.Data;

@Data
public class AlarmVO {
	private String id;
	private String name;
	
	public AlarmVO(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
