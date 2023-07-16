package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostMapper;
import com.groo.bear.pro.service.ProPostService;
import com.groo.bear.pro.service.ProPostUserVO;
import com.groo.bear.pro.service.ProPostVO;

@Service
public class ProPostServiceImpl implements ProPostService {
	@Autowired
	ProPostMapper ppm;
	
	@Override
	public ProPostVO readTopMenu(int proNo, String id) {
		return ppm.readTopMenu(proNo, id);
	}

	@Override
	public int readTopMenuCount(String id, int proNo) {
		return ppm.readTopMenuCount(id, proNo);
	}

	@Override
	public List<ProPostUserVO> readProjectParti(ProPostUserVO vo) {
		return ppm.readProjectParti(vo);
	}

}
