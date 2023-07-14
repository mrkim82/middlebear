package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.ProPostVO;

public interface ProPostMapper {
	//상단메뉴바
	// 상단메뉴바 조회
	public List<ProPostVO> readTopMenu(int proMemNo);
	
	// 상단메뉴바 인원수 조회
	public int readTopMenuCount(int proMemNo);
}
