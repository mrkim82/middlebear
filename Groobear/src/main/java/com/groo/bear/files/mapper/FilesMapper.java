package com.groo.bear.files.mapper;

import java.util.List;

import com.groo.bear.board.service.BoardVO;
import com.groo.bear.files.domain.FilesVO;

public interface FilesMapper {
	public int insert(FilesVO vo);
	
	public int delete(String uuid);
	
	public List<FilesVO> findByBno(int boardNo); //특정 게시물의 번호로 첨부파일을 찾는 작업
}