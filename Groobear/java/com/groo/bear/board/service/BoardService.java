package com.groo.bear.board.service;

import java.util.List;
import java.util.Map;

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
 
    


}
