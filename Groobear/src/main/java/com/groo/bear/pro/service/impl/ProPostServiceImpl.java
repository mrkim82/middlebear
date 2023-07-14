package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostMapper;
import com.groo.bear.pro.service.ProPostService;
import com.groo.bear.pro.service.ProPostVO;

@Service
public class ProPostServiceImpl implements ProPostService {
	@Autowired
	ProPostMapper ppm;
	
	@Override
	public List<ProPostVO> readTopMenu(int proMemNo) {
		return ppm.readTopMenu(proMemNo);
	}

	@Override
	public int readTopMenuCount(int proMemNo) {
		return ppm.readTopMenuCount(proMemNo);
	}

}
