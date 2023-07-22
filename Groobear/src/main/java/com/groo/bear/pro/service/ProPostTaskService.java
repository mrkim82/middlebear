package com.groo.bear.pro.service;

import java.util.List;

import com.groo.bear.pro.service.task.ProPostTaskVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkGroupVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkPersonVO;

public interface ProPostTaskService {
	//프로젝트(업무) 업무 전체 조회
	public List<ProPostTaskVO> readTaskAllList(int proNo);
	
	//프로젝트(업무) 업무 담당자 조회
	public List<ProPostTaskWorkPersonVO> readTaskWorkPerson(int proNo);
	
	//업무그룹 조회
	public List<ProPostTaskWorkGroupVO> readWorkGroup(int proNo);
}
