package com.groo.bear.pro.mapper;

import java.util.List;
import java.util.Map;

import com.groo.bear.pro.service.ProVO;

public interface ProMapper {
	//프로젝트 생성
	public void createPro(Map<String, Object> map);
	
	//프로젝트 조회
	public List<ProVO> readProject(String id);
	
	//프로젝트 즐겨찾기 등록
	public int updateStarY(int pMN);
	
	//프로젝트 즐겨찾기 취소
	public int updateStarN(int pMN);
//	
//	// 단건조회
//	public EmpVO selectEmpInfo(EmpVO empVO);
//	
//	//
//	public int insertEmpInfo(EmpVO empVO);
}
