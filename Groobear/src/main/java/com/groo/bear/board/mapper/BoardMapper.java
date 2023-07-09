package com.groo.bear.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.groo.bear.board.service.BoardVO;

public interface BoardMapper {
	
	@Select("select * from board")
	public List<BoardVO> selectAllList();
	
	public int selectBoard();
	
	public int insertBoard();
	
	public int updateBoard();
	
	public int deleteBoard();
}


