package com.groo.bear.mypage.service;

import java.util.List;

import com.groo.bear.paging.Criteria;

public interface CommuteService {

	//전체 조회
	public List<CommuteVO> getAllCommuteList(Criteria cri, CommuteVO commuteVO);
	//페이징
	public int commuteCnt(Criteria cri, CommuteVO commuteVO);
	//출근
	public int startWork(CommuteVO commuteVO);
	//퇴근 
	public int endWork(CommuteVO commuteVO);
	//퇴근없이 연장근무 시작
	public int ewStartOverWork(CommuteVO commuteVO);
	//연장근무 시작
	public int startOverWork(CommuteVO commuteVO);
	//연장근무 종료
	public int endOverWork(CommuteVO commuteVO);
	//근무기록 확인
	public CommuteVO chkWork(String id);
	//기록 조회
	public List<CommuteVO> getMyCommuteList(Criteria cri,String id,String monthDate);
	//초기값 지정
	public int settingDay(CommuteVO commuteVO);
	//개인 월별 기록
	public CommuteVO monthWork(String id, String monthDate);
	//수정
	public int commuteUpdate(CommuteVO commuteVO);
}
