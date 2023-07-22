package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostTaskMapper;
import com.groo.bear.pro.service.ProPostTaskService;
import com.groo.bear.pro.service.task.ProPostTaskVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkGroupVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkPersonVO;

@Service
public class ProPostTaskServiceImpl implements ProPostTaskService {
	
	@Autowired
	ProPostTaskMapper task;
	
	@Override
	public List<ProPostTaskVO> readTaskAllList(int proNo) {
		return task.readTaskAllList(proNo);
	}

	@Override
	public List<ProPostTaskWorkPersonVO> readTaskWorkPerson(int proNo) {
		return task.readTaskWorkPerson(proNo);
	}

	@Override
	public List<ProPostTaskWorkGroupVO> readWorkGroup(int proNo) {
		return task.readWorkGroup(proNo);
	}

}
