package com.groo.bear.mypage.mapper;

import java.util.List;

import com.groo.bear.mypage.service.CommuteVO;
import com.groo.bear.paging.Criteria;

public interface CommuteMapper {

	//근태 전체 조회
	public List<CommuteVO> selectAllCommute(Criteria cri,CommuteVO commuteVO);
	//페이징
	public int commuteListCnt(Criteria cri, CommuteVO commuteVO);
	//paging
	public int commuteListCnt2(Criteria cri, String id,String monthDate,CommuteVO commuteVO);
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
	//연장근무 계산
	public int overWorkTime(CommuteVO commuteVO);
	//근무기록 확인 
	public CommuteVO chkWork(String id);
	//개인 기록 조회
	public List<CommuteVO> getMyCommute(Criteria cri,String id, String monthDate);	
	//회원가입 초기값
	public int insertDay(String id);
	//개인 월별 출근 정보
	public CommuteVO calWork(String id,String monthDate);
	//개인 월별 결근 정보
	public CommuteVO calNoWork(String id, String monthDate);
	//개인정보 조회
	public List<CommuteVO> comInfo(String id);
	//전체 월별 정보
	public List<CommuteVO> calAllWork(Criteria cri, String id, String monthDate);
	//시간 수정
	public int commuteUpdate(CommuteVO commuteVO);
	
	
	
	
	
	
	
	
	
	
}
