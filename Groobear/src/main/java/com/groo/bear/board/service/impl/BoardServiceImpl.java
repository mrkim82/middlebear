package com.groo.bear.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.board.mapper.BoardMapper;
import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;
import com.groo.bear.files.FilesVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> selectAllList(BoardVO boardVO) {
		return boardMapper.selectAllList(boardVO);
	}

	@Override
	public BoardVO selectBoard(int boardNo) {
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
	public int addFiles(FilesVO filesVO) {
		return boardMapper.addFiles(filesVO);
	}

	
	
	
	
}
