package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.schvo.ProPostSchVO;

public interface ProPostSchMapper {
	//참석자 조회
	public List<ProPostSchVO> readSchparti(String id);
	
	//참석여부 변경
	public int updateSchPartiCheck(ProPostSchVO vo);
	
	//참석자 현황
	public List<ProPostSchVO> readPartiList(int proNo);
	
	// 참석자 전체 삭제
	public int deletePartiMemberAll(int schNo);
	
	//참석자 추가
	public boolean insertPartiMember(ProPostSchVO vo);
	
}
