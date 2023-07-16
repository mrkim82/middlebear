package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.ProPostUserVO;
import com.groo.bear.pro.service.ProPostVO;

public interface ProPostMapper {
	//상단메뉴바
	// 상단메뉴바 조회
	public ProPostVO readTopMenu(int proNo, String id);
	
	// 상단메뉴바 인원수 조회
	public int readTopMenuCount(String id, int proNo);
	
	// 프로젝트 참가자 정보
	public List<ProPostUserVO> readProjectParti(ProPostUserVO vo);
}
