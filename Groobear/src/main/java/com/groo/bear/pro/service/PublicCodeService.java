package com.groo.bear.pro.service;

import java.util.List;

import com.groo.bear.pro.service.provo.ProGroupVO;

public interface PublicCodeService {
	
	//전체 색상 조회
	public List<PublicCodeVO> readPublicCodeColorAll();
	
	//프로젝트 멤버 색상 변경
	public int updateProMemColor(ProGroupVO vo);
}
