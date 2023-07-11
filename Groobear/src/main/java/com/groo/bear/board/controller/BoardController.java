package com.groo.bear.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;

	// 전체조회페이지
	@GetMapping("boardList")
	public String boardList(Model model) {
		System.out.println(boardService.selectAllList());
		model.addAttribute("boardList", boardService.selectAllList());
		return "board/boardList";
	}

	// 단건조회페이지
	@GetMapping("getBoard")
	public String getBoard(Model model, @RequestParam int bNo) {
		model.addAttribute("getBoard", boardService.selectBoard(bNo));
		return "board/getBoard";
	}

	// 등록 페이지
	@GetMapping("boardInsert")
	public String boardInsertForm(Model model) {
		
		 model.addAttribute("BoardVO", new BoardVO()); System.out.println(model);
		 //if()
		 
		return "board/boardInsert";
	}

	// 등록 처리
	@PostMapping("boardInsert")
	public String boardInsert(BoardVO vo) {
		boardService.insertBoard(vo);
		return "board/boardInsert";
	}
}
