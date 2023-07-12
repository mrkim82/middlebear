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
	public List<BoardVO> selectAllList() {
		return boardMapper.selectAllList();
	}

	@Override
	public int selectBoard(int bNo) {
		return boardMapper.selectBoard(bNo);
	}

	@Override
	public int insertBoard(BoardVO vo) {
		return boardMapper.insertBoard(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return boardMapper.updateBoard(vo);
	}

	@Override
	public int deleteBoard(int bNo) {
		
		return boardMapper.deleteBoard(bNo);
	}

	@Override
	public int addFiles(FilesVO filesVO) {
		return boardMapper.addFiles(filesVO);
	}

	
	
	
	
}
