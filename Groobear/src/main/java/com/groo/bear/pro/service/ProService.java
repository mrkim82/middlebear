package com.groo.bear.pro.service;

import java.util.List;
import java.util.Map;

public interface ProService {
	//프로젝트 생성
	public void insertPro(Map<String, Object> map);
	
	//프로젝트 조회
	public List<ProVO> readProject(String id);
	public List<ProVO> readProjectHide(String id);
	public List<ProVO> readProjectStar(String id);
	
	//프로젝트 즐겨찾기 등록
	public int updateStarY(int pMN);
	
	//프로젝트 즐겨찾기 취소
	public int updateStarN(int pMN);
	
	//프로젝트 그룹
	// 프로젝트 그룹 목록 조회
	public List<ProGroupVO> readProjectGroup(String id);
	
	// 프로젝트 그룹 프로젝트 조회
	public List<ProGroupVO> readProjectGroupDetail(int groupNo, String id);
	
	// 프로젝트 그룹 생성
	public int createProjectGroup(String groupName, String id);
	
}
