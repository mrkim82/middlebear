package com.groo.bear.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProMapper;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProVO;

@Service
public class ProServiceImpl implements ProService {
	@Autowired
	ProMapper proMapper;
	
	//프로젝트 생성
	@Override
	public int createProject(ProVO proVO) {
		return proMapper.createProject(proVO);
	}

}
