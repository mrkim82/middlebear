package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.schvo.ProPostSchVO;

public interface ProPostSchMapper {
	//참석자 조회
	public List<ProPostSchVO> readSchparti(String id);
}