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
//	    log.info("register......" + boardVO);
		//시퀀스 생성
	    int boardNo = boardMapper.boardNoSequence();
	    
	    //게시판 글을 등록
	    boardVO.setBoardNo(boardNo);
	    int result = boardMapper.insertBoard(boardVO);
	    
	    //첨부파일 등록
	    List<FilesVO> fileList = boardVO.getAttachList();
	    // 첨부파일이 있을 경우에만 첨부파일을 등록
	    if(fileList != null && fileList.size() > 0) {
	    	fileList.forEach(attach -> {
	        	attach.setName("board_no");
	            attach.setNo(boardVO.getBoardNo());
	            System.out.println(attach);
	            attachMapper.insert(attach);
	        });
	    }
	    
	    // 첨부파일의 유무에 상관없이 
	    return result;
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
		return attachMapper.findByNo("board_no",boardNo);
	}
	//첨부파일 폴더 진짜 삭제
	@Transactional
	@Override
	public boolean remove(int boardNo) {
		log.info("remove .... " + boardNo);
		boardMapper.deleteCommentByBoardId(boardNo);
		attachMapper.deleteAll("board_no",boardNo);
		return mapper.deleteBoard(boardNo) == 1;
	}
	
	
	
	@Override
	public int updateBoardComment(BoardVO boardVO) {
		return boardMapper.updateBoardComment(boardVO);
	}
	@Transactional
	@Override
	public boolean modify(BoardVO boardVO) {
		
		log.info("modify................." + boardVO);
		
		attachMapper.deleteAll("board_no", boardVO.getBoardNo());
		
		boolean modifyResult = boardMapper.updateBoard(boardVO) == 1;
		
		if(modifyResult && boardVO.getAttachList() != null && boardVO.getAttachList().size() > 0) {
			boardVO.getAttachList().forEach(attach -> {
				attach.setName("board_no");
				attach.setNo(boardVO.getBoardNo());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}
	
}
