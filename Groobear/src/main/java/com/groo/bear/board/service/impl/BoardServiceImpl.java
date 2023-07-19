package com.groo.bear.board.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.board.mapper.BoardMapper;
import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.files.mapper.FilesMapper;
import com.groo.bear.paging.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardMapper boardMapper;

	
	@Override
	public BoardVO selectBoard(int boardNo) {
		boardMapper.updateCount(boardNo);
		return boardMapper.selectBoard(boardNo);
	}
	
	@Transactional
	@Override
	public int insertBoard(BoardVO boardVO) {
	    log.info("register......" + boardVO);
	    int boardNo = boardMapper.boardNoSequence();
	    boardVO.setBoardNo(boardNo);
	    
	    // Get the next boardNo from the sequence
	    // Set the boardNo in the boardVO object
	    boardVO.setBoardNo(boardNo);

	    if(boardVO.getAttachList() == null || boardVO.getAttachList().size() <= 0) {
	        return 0;
	    }
	    boardVO.getAttachList().forEach(attach -> {
	        // Use the boardNo for the attach object
	        attach.setBoardNo(boardNo);
	        attachMapper.insert(attach);
	    });
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
	
	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_= @Autowired)
	private FilesMapper attachMapper;

	@Override
	public List<FilesVO> getAttachList(int boardNo) {
		log.info("get Attach list by boardNo" + boardNo);
		return attachMapper.findByBno(boardNo);
	}
	
	
}
