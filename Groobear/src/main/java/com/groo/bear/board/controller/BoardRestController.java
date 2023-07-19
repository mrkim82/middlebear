package com.groo.bear.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;
import com.groo.bear.files.domain.FilesVO;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class BoardRestController {
	
	@Autowired
	BoardService boardService;
	
	//댓글 작성
	@PostMapping("boardCreateComment")
	public Map<String, Object> createPostComment(HttpServletRequest request, @RequestBody BoardVO boardVO) {
		System.out.println("왜안옴"+boardVO);
		HttpSession session = request.getSession();
		Map <String, Object> map = new HashMap<>();
		String res = "";
		int result = 0;
		
		boardVO.setId((String)session.getAttribute("Id"));
		
		boardService.createBoardComment(boardVO);
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		
		map.put("result", res);
		
		return map;
	}
	
	//댓글 수정
	@PutMapping("boardUpdateComment")
	public Map<String, Object> proGroupUpdate(@RequestBody BoardVO boardVO) {
		Map <String, Object> map = new HashMap<>();
		String res;
		
		int result = boardService.updateBoardComment(boardVO);
		
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		
		map.put("result", res);
		
		return map;
	}
	
	//댓글 삭제
	@PostMapping("boardDeleteComment")
	public Map<String, Object> deletePostComment(HttpServletRequest request, @RequestBody BoardVO boardVO) {
		Map <String, Object> map = new HashMap<>();
		String res = "";
		int result = boardService.deleteBoardComment(boardVO.getComNo());
		System.out.println(boardVO.getBoardNo());
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		map.put("result", res);
		System.out.println(boardVO);
		System.out.println(result);
		return map;
	}
	
	// 첨부파일을 Ajax로 처리 했다면 서버에서 JSON 데이터를 만들어서 화면에 전송하는 작업 하기 위함.
	@GetMapping(value ="/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FilesVO>> getAttachList(@RequestParam int boardNo) {
		log.info("getAttachList" + boardNo);
		return new ResponseEntity<>(boardService.getAttachList(boardNo), HttpStatus.OK);
	}
}
