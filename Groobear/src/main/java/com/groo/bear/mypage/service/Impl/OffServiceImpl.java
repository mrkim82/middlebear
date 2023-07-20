package com.groo.bear.mypage.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.mypage.mapper.OffMapper;
import com.groo.bear.mypage.service.OffService;
import com.groo.bear.mypage.service.OffVO;

@Service
public class OffServiceImpl implements OffService {
	
	@Autowired
	OffMapper offMapper;

	@Override
	public int firstSetOff(OffVO offVO) {
		return offMapper.setOff(offVO);
	}



	
	
	
	

	
		
}	
