package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.todovote.CreateVoteVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCountVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreListVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreVO;
import com.groo.bear.pro.service.todovote.ProPostTodoVO;
import com.groo.bear.pro.service.todovote.ProPostVoteVO;
import com.groo.bear.pro.service.todovote.ProTodoPartiCountVO;
import com.groo.bear.pro.service.todovote.ProUpdateVoteVO;
import com.groo.bear.pro.service.todovote.ProVoteCDVO;

public interface ProTodoNVoteMapper {

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
	
	//할 일 생성
	public int createPostTodo(ProPostTodoCreVO vo);
	//할 일 내용
	public int createTodo(ProPostTodoCreListVO todoDetail);
	
	//투표글 작성(5번)
	public void createPostVote(CreateVoteVO vo);
	
	//투표 마감
	public int updateVoteStatus(int voteNo);
	
	//투표 참가 인원수 조회
	public List<ProTodoPartiCountVO> readVotePartiCount(int proNo);
	
	//투표하기
	public int insertVote(ProVoteCDVO vo);
	
	//투표취소
	public int deleteVote(ProVoteCDVO vo);
	
	//투표 체크 조회하기 위한 select
	public List<ProVoteCDVO> readxxVote(String id, int proNo);
	
	//투표 내용변경
	public int updateVote(ProUpdateVoteVO vo);
	
	//추가
	public int createVoteDetail(String voteDetailContent, int voteNo);
	
}
