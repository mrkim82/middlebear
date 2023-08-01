package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.ProFileVO;
import com.groo.bear.pro.service.postvo.ProDetailSearchVO;
import com.groo.bear.pro.service.postvo.ProInviteMailVO;
import com.groo.bear.pro.service.postvo.ProPartiListVO;
import com.groo.bear.pro.service.postvo.ProPostChartVO;
import com.groo.bear.pro.service.postvo.ProPostCommentVO;
import com.groo.bear.pro.service.postvo.ProPostFeedVO;
import com.groo.bear.pro.service.postvo.ProPostUserVO;
import com.groo.bear.pro.service.postvo.ProPostVO;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.postvo.ProPostWritingVO;
import com.groo.bear.pro.service.postvo.ProWritingUVO;

public interface ProPostMapper {
	//상단메뉴바
	// 상단메뉴바 조회
	public ProPostVO readTopMenu(int proNo, String id);
	
	// 상단메뉴바 인원수 조회
	public int readTopMenuCount(String id, int proNo);
	
	// 프로젝트 참가자 정보
	public List<ProPostUserVO> readProjectParti(int proNo);
	
	// 게시글 조회
	public List<ProPostFeedVO> readFeedPost(int proNo, int postType);
	// 게시글 삭제
	public int deleteProPost(int proPostNo);
	
	// 글 작성
	public void createPostWriting(ProPostWritingVO vo);
	// 글 조회
	public List<ProPostWritingVO> readPostWriting(int postType);
	// 글 댓글 조회
	public List<ProPostCommentVO> readPostWritingComment(int proNo);
	
	// 업무 작성
	public void createPostWork(ProPostWorkVO vo);
	
	//댓글
	// 댓글 작성
	public int createPostComment(ProPostCommentVO vo);
	// 댓글 삭제
	public int deletePostComment(int comNo);
	// 댓글 수정
	public int updatePostComment(ProPostCommentVO vo);
	
	//게시글 조회 업무 상태 변경
	public int updateWorkPostStatus(ProPostWorkVO vo);
	
	//차트조회
	public List<ProPostChartVO> readPostChart(int proNo);
	
	// 프로필 메모 수정
	public int updateProfileMemo(ProPostUserVO vo);
	
	//게시글 수정
	// 게시글 제목 변경
	public int updateProPostTitle(String postTitle, int proPostNo);
	// 글 내용변경
	public int updateProWriting(ProWritingUVO vo);
	  
	//프로젝트 내 검색
	public List<ProDetailSearchVO> readProInSearch(ProDetailSearchVO vo);
	
	//프로젝트 초대 리스트
	public List<ProPartiListVO> readPartiListM(int proNo);
	//프로젝트 초대
	public int createInviteMail(ProInviteMailVO vo);
	
	//프로젝트 삭제
	public int deletePro(int proNo);
	
	// 프로젝트 게시물 마지막 고유번호
	public int readProPostNo();
}
