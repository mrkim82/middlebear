package com.groo.bear.pro.service;

import java.util.List;

public interface PublicCodeColorService {
	
	//전체 색상 조회
	public List<PublicCodeColorVO> readPublicCodeColorAll();
	
	//프로젝트 멤버 색상 변경
	public int updateProMemColor(ProGroupVO vo);
}
