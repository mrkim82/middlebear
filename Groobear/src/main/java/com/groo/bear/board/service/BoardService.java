package com.groo.bear.board.service;

import java.util.List;

import com.groo.bear.files.domain.FilesVO;

public interface BoardService {
	//전체조회 페이징
	public List<BoardVO> selectAllListPaged(BoardVO boardVO, int startRow, int endRow);
	
	//전체조회
	public List<BoardVO> selectAllList(BoardVO boardVO);
	//게시글 단건조회
	public BoardVO selectBoard(int boardNo);
	
	//게시글 등록
	public int insertBoard(BoardVO boardVO);
	
	//게시글 수정
	public int updateBoard(BoardVO boardVO);
	
	//게시글 삭제
	public int deleteBoard(int boardNo);
	
	//첨부파일
	public int addFiles(FilesVO filesVO);

}
