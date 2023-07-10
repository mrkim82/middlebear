package com.groo.bear.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	
	
}
