package com.groo.bear.pro.service;

import java.util.List;

import com.groo.bear.pro.service.postvo.ProPostChartVO;
import com.groo.bear.pro.service.postvo.ProPostCommentVO;
import com.groo.bear.pro.service.postvo.ProPostFeedVO;
import com.groo.bear.pro.service.postvo.ProPostVO;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.postvo.ProPostWritingVO;

public interface ProPostService {
	//상단메뉴바
	// 상단메뉴바 조회
	public ProPostVO readTopMenu(int proNo, String id);
	
	// 상단메뉴바 인원수 조회
	public int readTopMenuCount(String id, int proNo);
	
	// 프로젝트 참가자 정보
	public List<ProPostUserVO> readProjectParti(int proNo);
	
	// 게시글 조회
	public List<ProPostFeedVO> readFeedPost(int proNo);
		
	// 글 작성
	public void createPostWriting(ProPostWritingVO vo);
	// 글 조회
	public List<ProPostWritingVO> readPostWriting(int postType);
	// 글 댓글 조회
	public List<ProPostCommentVO> readPostWritingComment(int proNo);
	
	//업무 작성
	public void createPostWork(ProPostWorkVO vo);
	
	//댓글
	// 댓글 작성
	public String createPostComment(ProPostCommentVO vo);
	// 댓글 삭제
	public String deletePostComment(int comNo);
	// 댓글 수정
	public String updatePostComment(ProPostCommentVO vo);
	
	//게시글 조회 업무 상태 변경
	public String updateWorkPostStatus(ProPostWorkVO vo);
	
	//차트 조회
	public List<ProPostChartVO> readPostChart(int proNo);
}
