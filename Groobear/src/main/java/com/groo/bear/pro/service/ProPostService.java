package com.groo.bear.pro.service;

import java.util.List;

import com.groo.bear.pro.service.postvo.ProPostCommentVO;
import com.groo.bear.pro.service.postvo.ProPostWorkGroupVO;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.postvo.ProPostWritingVO;

public interface ProPostService {
	//상단메뉴바
	// 상단메뉴바 조회
	public ProPostVO readTopMenu(int proNo, String id);
	
	// 상단메뉴바 인원수 조회
	public int readTopMenuCount(String id, int proNo);
	
	// 프로젝트 참가자 정보
	public List<ProPostUserVO> readProjectParti(ProPostUserVO vo);
	
	// 글 작성
	public void createPostWriting(ProPostWritingVO vo);
	// 글 조회
	public List<ProPostWritingVO> readPostWriting(int postType);
	// 글 댓글 조회
	public List<ProPostCommentVO> readPostWritingComment(int proPostNo);
	
	//업무 작성
	public void createPostWork(ProPostWorkVO vo);
	//업무 그룹조회
	public List<ProPostWorkGroupVO> readWritingWorkGroup(int proNo);
	
	// 댓글 작성
	public int createPostComment(ProPostCommentVO vo);
	// 댓글 삭제
	public int deletePostComment(int comNo);
	
}
