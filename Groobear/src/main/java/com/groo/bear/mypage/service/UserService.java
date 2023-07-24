package com.groo.bear.mypage.service;

import com.groo.bear.emp.service.EmpVO;

public interface UserService {

	public EmpVO checkGrade(String id);
	//마이페이지 비밀번호 확인
	public EmpVO checkMypage(EmpVO vo);
	//암호화된 비밀번호 조회(비교용)
	public EmpVO checkScPw(String id);
	
	//프로필사진 등록
	public int insertProfImg(EmpVO vo);
	
	//프로필사진 삭제
	public int deleteProfImg(String id);
	
	//회원정보 수정
	public int updateUsers(EmpVO vo);
	
	//마이프로필 확인
	public EmpVO checkMyProf(String id);
}
