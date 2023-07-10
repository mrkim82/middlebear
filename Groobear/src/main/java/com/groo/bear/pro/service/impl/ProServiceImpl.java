package com.groo.bear.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProMapper;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProVO;

@Service
public class ProServiceImpl implements ProService {
	@Autowired
	ProMapper proMapper;

	@Override
	public void insertPro(Map<String, Object> map) {
		proMapper.createPro(map);
		System.out.println("서비스임플" + map);
	}

	@Override
	public List<ProVO> readProject(String id) {
		return proMapper.readProject(id);
	}

	@Override
	public int updateStarY(int pMN) {
		return proMapper.updateStarY(pMN);
	}

	@Override
	public int updateStarN(int pMN) {
		return proMapper.updateStarN(pMN);
	}

}
