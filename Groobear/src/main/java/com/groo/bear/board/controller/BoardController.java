package com.groo.bear.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
//	@GetMapping("boardList")
//	public String boardList(Model model, @ModelAttribute BoardVO boardVO, Pageable pageable) {
//		Page<BoardVO> page = boardService.selectAllListPaged(boardVO, pageable);
//		model.addAttribute("boardList", page.getContent());
//		model.addAttribute("page", page);
//		model.addAttribute("boardVO", boardVO);
//		return "board/boardList";
//	}
	
	@GetMapping("boardList")
	public String boardList(Model model, @ModelAttribute BoardVO boardVO) {
		model.addAttribute("boardList", boardService.selectAllList(boardVO));
		return "board/boardList";
	}
	
	@PostMapping("searchList")
	@ResponseBody
	public List<BoardVO> searchList(@RequestBody BoardVO boardVO) {
		System.out.println(boardVO);
		return boardService.selectAllList(boardVO);
	}
	
	@GetMapping("boardInfo")
	public String getBoard(Model model, @RequestParam int boardNo) {
		model.addAttribute("board", boardService.selectBoard(boardNo));
		return "board/boardInfo";
	}
	
	@GetMapping("boardInsert")
	public String boardInsertForm(Model model) {
		model.addAttribute("board", new BoardVO());
		return "board/boardInsert";
	}
	
	@PostMapping("boardInsert")
	public String boardInsert(@ModelAttribute BoardVO boardVO, Model model) {
		boardService.insertBoard(boardVO);
		return "redirect:/boardList?boardType=" + boardVO.getBoardType();
	}
	
	@GetMapping("boardUpdate/{boardNo}")
	public String getUpdatePage(@PathVariable("boardNo") int boardNo, Model model) {
	    BoardVO board = boardService.selectBoard(boardNo);
	    model.addAttribute("board", board);
	    return "board/boardUpdate";
	}

	@PostMapping("boardUpdate/{boardNo}")
	public String boardUpdate(@PathVariable("boardNo") int boardNo, @ModelAttribute BoardVO boardVO, Model model) {
		boardVO.setBoardNo(boardNo);
		boardService.updateBoard(boardVO);
		return "redirect:/boardInfo?boardNo=" + boardNo;
	}
	
	@DeleteMapping("boardDelete/{boardNo}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String boardDelete(@PathVariable("boardNo") int boardNo) {
	    boardService.deleteBoard(boardNo);
	    return "게시글이 삭제되었습니다.";
	}
}
