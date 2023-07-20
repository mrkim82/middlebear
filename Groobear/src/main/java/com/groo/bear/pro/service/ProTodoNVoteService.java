package com.groo.bear.pro.service;

import java.util.List;

import com.groo.bear.pro.service.todovote.ProPostTodoVO;

public interface ProTodoNVoteService {
	//프로젝트 일정 참여자 조회
	public List<ProPostTodoVO> readTodoList(int proNo);
	
	//일정 체크 상태 변경
	public int updateTotoStatus(ProPostTodoVO vo);
}
