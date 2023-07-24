package com.groo.bear.mypage.mapper;

import java.util.List;

import com.groo.bear.mypage.service.CommuteVO;
import com.groo.bear.paging.Criteria;

public interface CommuteMapper {

	//근태 전체 조회
	public List<CommuteVO> selectAllCommute(Criteria cri,CommuteVO commuteVO);
	//페이징
	public int commuteListCnt(Criteria cri, CommuteVO commuteVO);
		
	//출근
	public int workStart(CommuteVO commuteVO); 
	//퇴근
	public int workEnd(CommuteVO commuteVO);
	//퇴근없이 연장근무 시작
	public int overWorkStartEW(CommuteVO commuteVO);
	//연장근무 시작
	public int overWorkStart(CommuteVO commuteVO);
	//연장근무 종료
	public int overWorkEnd(CommuteVO commuteVO);
	//근무기록 확인 
	public CommuteVO chkWork(String id);
	//개인 기록 조회
	public List<CommuteVO> getMyCommute(Criteria cri, String id);	
	
	
}
