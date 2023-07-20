package com.groo.bear.emp.mapper;

import java.util.List;

import com.groo.bear.emp.service.EmpVO;

public interface EmpMapper {
	//사원정보조회
	public EmpVO getEmpInfo(EmpVO vo);
	//비밀번호 변경
	public int changePw(EmpVO vo);
	//회원가입 인사정보 조회
	public List<EmpVO> searchEmpInfo(String name); 
	//아이디 중복체크
	public int checkId(String id);
	//연락처 중복체크
	public int checkPhone(String phone);
	//회원가입
	public int signUp(EmpVO vo);
}
