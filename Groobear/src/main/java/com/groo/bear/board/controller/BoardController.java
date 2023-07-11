package com.groo.bear.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//전체조회페이지
	@GetMapping("boardList")
	public String boardList(Model model  ) {
		model.addAttribute("boardList", boardService.selectAllList());
		return "board/boardList";
	}
//	//전체조회페이지 안의 검색
//	@GetMapping("boardSearch")
//	@ResponseBody
//	public String boardSearch()
	
	
	//단건조회페이지
	@GetMapping("getBoard")
	public String getBoard(Model model, @RequestParam int boardNo) {
		model.addAttribute("getBoard", boardService.selectBoard(boardNo));
		return "board/getBoard";
	}
	
	//등록페이지
	@GetMapping("boardInsert")
	public String boardInsertForm() {
	    return "board/boardInsert";
	}
	
	//등록 처리
	@PostMapping("boardInsert")
	public String boardInsert(Model model, BoardVO vo) {
		model.addAttribute("boardInsert", boardService.insertBoard(vo));
		return "redirect:/boardList";
	}
	
	
}
