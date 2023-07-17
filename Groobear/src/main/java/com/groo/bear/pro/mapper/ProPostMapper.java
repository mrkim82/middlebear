package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.ProPostVO;

public interface ProPostMapper {
	//상단메뉴바 조회
	public List<ProPostVO> readTopMenu(int proMemNo);
	
}
