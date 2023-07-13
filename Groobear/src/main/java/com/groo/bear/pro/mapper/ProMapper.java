package com.groo.bear.pro.mapper;

import java.util.List;
import java.util.Map;

import com.groo.bear.pro.service.ProGroupVO;
import com.groo.bear.pro.service.ProVO;

public interface ProMapper {
	//프로젝트 생성
	public void createPro(Map<String, Object> map);
	
	//프로젝트 조회
	public List<ProVO> readProject(Map<String, Object> map);
	public List<ProVO> readProjectHide(String id);
	public List<ProVO> readProjectStar(String id);
	public List<ProVO> readProjectNoGroup(String id);
	
	//프로젝트 참가자수 조회
	public List<ProVO> readProjectParti(String id);
	
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
	
	// 프로젝트 그룹 생성 후 번호 조회
	public int readProjectGroupNo();
	
	//프로젝트 그룹 이름 수정
	public int updateGroupName(ProGroupVO vo);
	
	//프로젝트 그룹 삭제
	public int deleteGroup(int groupNo);
//	
//	// 단건조회
//	public EmpVO selectEmpInfo(EmpVO empVO);
//	
}
