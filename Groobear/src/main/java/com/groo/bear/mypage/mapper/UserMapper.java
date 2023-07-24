package com.groo.bear.mypage.mapper;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.mypage.service.CommuteVO;

public interface UserMapper {

	
	public EmpVO checkGrade(String id);
	//마이페이지 비밀번호 확인
	public EmpVO checkMypage(EmpVO vo);
	//암호화된 비밀번호 조회(비교용)
	public EmpVO checkScPw(String id);
	//회원정보 수정
	public int updateUsers(EmpVO vo);
	//마이프로필 확인
	public EmpVO checkMyProf(String id);
	
	
	
	
}
