package com.groo.bear.pro.service;

import java.util.List;

import com.groo.bear.pro.service.todovote.CreateVoteVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCountVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreVO;
import com.groo.bear.pro.service.todovote.ProPostTodoVO;
import com.groo.bear.pro.service.todovote.ProPostVoteVO;
import com.groo.bear.pro.service.todovote.ProTodoPartiCountVO;

public interface ProTodoNVoteService {
	//프로젝트 일정 참여자 조회
	public List<ProPostTodoVO> readTodoList(int proNo);
	
	//일정 체크 상태 변경
	public int updateTotoStatus(ProPostTodoVO vo);
	
	//게시물 당 전체 todo갯수
	public List<ProPostTodoCountVO> readAllTodoListPer(int proNo);
	
	//프로젝트 vote조회
	public List<ProPostVoteVO> readVoteList(int proNo);
	
	//투표 항목 조회
	public List<ProPostVoteVO> readVoteListCheck(int proNo);
	
	//투표 참여자 조회
	public List<ProPostVoteVO> readVoteListParti(int proNo);
	
	//할 일 생성(총)
	public int createTodo(ProPostTodoCreVO vo);
	
	//투표글 작성(5번)
	public void createPostVote(CreateVoteVO vo);
	
	//투표 마감
	public int updateVoteStatus(int voteNo);
	
	//투표 참가 인원수 조회
	public List<ProTodoPartiCountVO> readVotePartiCount(int proNo);
}
