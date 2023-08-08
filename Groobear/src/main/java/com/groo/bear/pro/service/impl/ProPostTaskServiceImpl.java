package com.groo.bear.pro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostMapper;
import com.groo.bear.pro.mapper.ProPostTaskMapper;
import com.groo.bear.pro.service.ProPostTaskService;
import com.groo.bear.pro.service.task.ProPostTaskDetailVO;
import com.groo.bear.pro.service.task.ProPostTaskVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkGroupVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkPersonVO;
import com.groo.bear.pro.service.task.ProUpWorkVo;
import com.groo.bear.pro.service.task.ProWorkViewVO;

@Service
public class ProPostTaskServiceImpl implements ProPostTaskService {
	
	@Autowired
	ProPostTaskMapper task;
	
	@Autowired
	ProPostMapper ppm;
	
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

	@Override
	public int deleteWorkPerson(String id) {
		return task.deleteWorkPerson(id);
	}

	@Override
	public int createWorkGroup(ProPostTaskWorkGroupVO vo) {
		return task.createWorkGroup(vo);
	}

	@Override
	public int deleteWorkGroup(int workGroupNo) {
		return task.deleteWorkGroup(workGroupNo);
	}

	@Override
	public List<String> readDetailWorkPerson(int proPostNo) {
		return task.readDetailWorkPerson(proPostNo);
	}

	@Override
	public int updateWorkPost(ProUpWorkVo vo) {
		//제목 변경
		ppm.updateProPostTitle(vo.getPostTitle(), vo.getProPostNo());
		//내용 변경
		task.updateProWork(vo);
		
		//담당자 전체 삭제
		task.deleteWorkMemberAll(vo);
		//담당자 추가
		if(!vo.getIds().isEmpty()) {
			
			for (int i = 0; i < vo.getIds().size(); i++) {
				task.createWorkMember(vo.getProPostNo(), vo.getIds().get(i));
			};
		};
		
		task.updateProWorkDate(vo.getProPostNo());
		return 0;
	}

	@Override
	public List<ProPostTaskVO> readGantt(int proNo) {
		return task.readGantt(proNo);
	}

}
