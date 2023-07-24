package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.ProGroupVO;
import com.groo.bear.pro.service.PublicCodeColorVO;

public interface PublicCodeColorMapper {
	
	//전체 색상 조회
	public List<PublicCodeColorVO> readPublicCodeColorAll();
	
	//프로젝트 멤버 색상 변경
	public int updateProMemColor(ProGroupVO vo);
}
