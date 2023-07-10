package com.groo.bear.pro.service;

import java.util.List;
import java.util.Map;

public interface ProService {
	//프로젝트 생성
	public void insertPro(Map<String, Object> map);
	
	//프로젝트 조회
	public List<ProVO> readProject(String id);
	
	//프로젝트 즐겨찾기 등록
	public int updateStarY(int pMN);
	
	//프로젝트 즐겨찾기 취소
	public int updateStarN(int pMN);
	
	
}
