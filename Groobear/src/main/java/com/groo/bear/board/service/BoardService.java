package com.groo.bear.board.service;

import java.util.List;

import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.paging.Criteria;

public interface BoardService {
	
	//전체조회
	public List<BoardVO> selectAllList(Criteria cri,BoardVO boardVO);
	//게시글 단건조회
	public BoardVO selectBoard(int boardNo);
	
	//게시글 등록
	public int insertBoard(BoardVO boardVO);
	
	//게시글 수정
	public int updateBoard(BoardVO boardVO);
	
	//게시글 삭제
	public int deleteBoard(int boardNo);
	
	//첨부파일
	//public int addFiles(FilesVO filesVO);
	
	//페이징
	public int boardListCnt(Criteria cri, BoardVO boardVO);
	//댓글
	//댓글 전체조회
	public List<BoardVO> readBoardComment(int boardNo);
	
	//댓글 생성
	public int createBoardComment(BoardVO boardVO);
	
	//댓글 삭제
	public int deleteBoardComment(int comNo);
	
	//댓글 수정
	public int updateBoardComment(BoardVO boardVO);

	//첨부파일 조회
	public List<FilesVO> getAttachList(int boardNo);
	
}
