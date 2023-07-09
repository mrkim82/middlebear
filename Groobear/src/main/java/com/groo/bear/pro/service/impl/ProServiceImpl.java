package com.groo.bear.pro.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProMapper;
import com.groo.bear.pro.service.ProService;

@Service
public class ProServiceImpl implements ProService {
	@Autowired
	ProMapper proMapper;
	
	//프로젝트 생성
//	@Override
//	public int createProject(ProVO proVO) {
//		return proMapper.createProject(proVO);
//	}

	@Override
	public void insertPro(Map<String, Object> map) {
		proMapper.createPro(map);
		System.out.println("서비스임플" + map);
	}

}
