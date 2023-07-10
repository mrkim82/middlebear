package com.groo.bear.board.mapper;

import java.util.List;

import com.groo.bear.board.service.BoardVO;


public interface BoardMapper {
	//게시글 전체조회
	public List<BoardVO> selectAllList();
	
	//게시글 단건조회
	public int selectBoard(int bNo);
	
	//게시글 등록
	public int insertBoard(BoardVO vo);
	
	//게시글 수정
	public int updateBoard(BoardVO vo);
	
	//게시글 삭제
	public int deleteBoard(int bNo);
}


