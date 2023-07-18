package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostSchMapper;
import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.schvo.ProPostSchVO;

@Service
public class ProPostSchServiceImpl implements ProPostSchService {
	@Autowired
	ProPostSchMapper ppsm;
	
	@Override
	public List<ProPostSchVO> readSchparti(String id) {
		return ppsm.readSchparti(id);
	}

	@Override
	public int updateSchPartiCheck(ProPostSchVO vo) {
		return ppsm.updateSchPartiCheck(vo);
	}

	@Override
	public List<ProPostSchVO> readPartiList(int proNo) {
		return ppsm.readPartiList(proNo);
	}

}
