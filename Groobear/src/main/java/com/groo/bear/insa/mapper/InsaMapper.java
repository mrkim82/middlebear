package com.groo.bear.insa.mapper;

import java.util.List;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.paging.Criteria;

public interface InsaMapper {
	//회원 전체조회
	public List<EmpVO> selectEmpList(Criteria cri, EmpVO vo);
	//페이징
	public int EmpListCnt(Criteria cri, EmpVO vo);
	//회원 상세조회
	public EmpVO empDetailInfo(String id);
	//부서 정보 전체조회
	public List<EmpVO> getDept();
	//UserInfo 업데이트
	public int userInfoUpdate(EmpVO vo);
	//Users 업데이트
	public int usersUpdate(EmpVO vo);
	//Users 삭제
	public int usersDelete(int empNo);
	//인사관리 전체조회
	public List<EmpVO> userInfoList(Criteria cri, EmpVO vo);	
	//페이징
	public int userInfoListCnt(Criteria cri, EmpVO vo);
	//UserInfo 삭제
	public int userInfoDel(EmpVO vo);
	//UserInfo 등록
	public int userInfoAdd(EmpVO vo);
	//주민번호 중복체크
	public int checkPno(String pno);
	//인사정보 업데이트
	public int updateUserInfo(EmpVO vo);
}
