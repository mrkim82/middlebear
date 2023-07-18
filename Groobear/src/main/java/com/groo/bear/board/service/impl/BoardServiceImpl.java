package com.groo.bear.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.board.mapper.BoardMapper;
import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;
import com.groo.bear.paging.Criteria;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardMapper boardMapper;

	
	@Override
	public BoardVO selectBoard(int boardNo) {
		boardMapper.updateCount(boardNo);
		return boardMapper.selectBoard(boardNo);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		return boardMapper.insertBoard(boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return boardMapper.updateBoard(boardVO);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardMapper.deleteBoard(boardNo);
	}

	@Override
	public int boardListCnt(Criteria cri, BoardVO boardVO){
        return boardMapper.boardListCnt(cri, boardVO);
	}
	@Override
	 public List<BoardVO> selectAllList(Criteria cri, BoardVO boardVO){
	        return boardMapper.selectAllList(cri, boardVO);
	}

	@Override
	public int createBoardComment(BoardVO boardVO) {
		return boardMapper.createBoardComment(boardVO);
	}

	@Override
	public int deleteBoardComment(int comNo) {
		return boardMapper.deleteBoardComment(comNo);
	}

	@Override
	public int updateBoardComment(BoardVO boardVO) {
		return boardMapper.updateBoardComment(boardVO);
	}

	@Override
	public List<BoardVO> readBoardComment(int boardNo) {
		return boardMapper.readBoardComment(boardNo);
	}
	
	
}
