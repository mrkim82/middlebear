package com.groo.bear.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//전체조회페이지
	@GetMapping("boardList")
	public String boardList(Model model, @ModelAttribute BoardVO boardVO) {
		model.addAttribute("boardList", boardService.selectAllList(boardVO));
		return "board/boardList";
	}
	
	//검색
	@PostMapping("searchList")
	@ResponseBody
	public List<BoardVO> searchList(@RequestBody BoardVO boardVO) {
		System.out.println(boardVO);
		return boardService.selectAllList(boardVO);
	}
	
	//단건조회페이지
	@GetMapping("boardInfo")
	public String getBoard(Model model, @RequestParam int boardNo) {
		model.addAttribute("board", boardService.selectBoard(boardNo));
		return "board/boardInfo";
	}
	
	//등록페이지
	@GetMapping("boardInsert")
	public String boardInsertForm() {
	    return "board/boardInsert";
	}
	
	//등록 처리
	@PostMapping("boardInsert")
	public String boardInsert(Model model, BoardVO boardVO) {
		System.out.println(boardVO);
		model.addAttribute("boardInsert", boardService.insertBoard(boardVO));
		String boardType = boardVO.getBoardType();
		System.out.println(boardType);
		return "redirect:/boardList?boardType=" + boardType;
	}
	
	//수정페이지
	@RequestMapping(value = "boardUpdate/{boardNo}", method = {RequestMethod.GET, RequestMethod.POST})
	public String getUpdatePage(@PathVariable("boardNo") int boardNo, Model model) {
	    BoardVO board = boardService.selectBoard(boardNo);
	    model.addAttribute("board", board);
	    return "board/boardUpdate";
	}

	//수정처리
	@PostMapping("boardUpdate")
	public String boardUpdate(@RequestParam int boardNo, BoardVO boardVO, Model model) {
		boardVO.setBoardNo(boardNo);
		model.addAttribute("board", boardService.updateBoard(boardVO));
		return "redirect:/boardInfo?boardNo=" + boardNo;
	}
	
	//삭제처리
	@DeleteMapping("boardDelete/{boardNo}")
	@ResponseStatus(HttpStatus.OK)
//	@PostMapping("boardDelete/{boardNo}")
//	@ResponseBody
	public void boardDelete(@PathVariable("boardNo") int boardNo) {
	    boardService.deleteBoard(boardNo);
	}

//	@GetMapping("boardDelete")
//	public String boardDelete(@RequestParam int boardNo) {
//		boardService.deleteBoard(boardNo);
//		return "boardList";
//	}
	
}
