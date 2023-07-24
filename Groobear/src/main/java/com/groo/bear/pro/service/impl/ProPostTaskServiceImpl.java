package com.groo.bear.pro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostTaskMapper;
import com.groo.bear.pro.service.ProPostTaskService;
import com.groo.bear.pro.service.task.ProPostTaskDetailVO;
import com.groo.bear.pro.service.task.ProPostTaskVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkGroupVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkPersonVO;
import com.groo.bear.pro.service.task.ProWorkViewVO;

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

	@Override
	public List<ProPostTaskDetailVO> readWorkDetail(int proNo) {
		return task.readWorkDetail(proNo);
	}

	@Override
	public ProWorkViewVO readWorkView(int proNo, String id) {
		return task.readWorkView(proNo, id);
	}

	@Override
	public int updateWorkView(ProWorkViewVO vo) {
		vo.setWorkName(Optional.ofNullable(vo.getWorkName()).orElse("N"));
	    vo.setWorkStatus(Optional.ofNullable(vo.getWorkStatus()).orElse("N"));
	    vo.setWorkPri(Optional.ofNullable(vo.getWorkPri()).orElse("N"));
	    vo.setWorkManager(Optional.ofNullable(vo.getWorkManager()).orElse("N"));
	    vo.setWorkStartDay(Optional.ofNullable(vo.getWorkStartDay()).orElse("N"));
	    vo.setWorkEndDay(Optional.ofNullable(vo.getWorkEndDay()).orElse("N"));
	    vo.setWorkDate(Optional.ofNullable(vo.getWorkDate()).orElse("N"));
	    vo.setWorkNum(Optional.ofNullable(vo.getWorkNum()).orElse("N"));
	    vo.setWorkWriter(Optional.ofNullable(vo.getWorkWriter()).orElse("N"));
	    vo.setWorkUpdateDay(Optional.ofNullable(vo.getWorkUpdateDay()).orElse("N"));
	    
		return task.updateWorkView(vo);
	}

}
