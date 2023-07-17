package com.groo.bear.board.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;
import com.groo.bear.paging.Criteria;
import com.groo.bear.paging.Paging;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/boardList")
	public String getboardList(Criteria cri, Model model, BoardVO boardVO) {
		// 전체 글 개수
        int boardListCnt = boardService.boardListCnt(cri, boardVO);
        
        // 페이징 객체
        Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(boardListCnt);
		
		model.addAttribute("boardList", boardService.selectAllList(cri, boardVO));
		model.addAttribute("paging", paging);
		
		return "board/boardList";
	}
//	@GetMapping("/uploadAjax")
//	public void uploadAjax() {
//		log.info("upload ajax");
//	}
	
	@GetMapping("boardInfo")
	public String getBoard(Model model, @RequestParam int boardNo) {
		model.addAttribute("board", boardService.selectBoard(boardNo));
		return "board/boardInfo";
	}
	
	//등록 페이지
	@GetMapping("boardInsert")
	public String boardInsertForm(Model model, BoardVO vo) {
		System.out.println(vo);
		model.addAttribute("board", vo);
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

	@PostMapping("boardUpdate")
	public String boardUpdate(BoardVO boardVO, Model model) {
		System.out.println(boardVO);
		boardService.updateBoard(boardVO);
		return "redirect:/boardInfo?boardNo=" + boardVO.getBoardNo();
	}
	
	@DeleteMapping("boardDelete/{boardNo}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String boardDelete(@PathVariable("boardNo") int boardNo) {
	    boardService.deleteBoard(boardNo);
	    return "게시글이 삭제되었습니다.";
	}
	
	/*
	 * @RequestMapping(value="boardList") public String boardList(Criteria cri,
	 * Model model) throws Exception {
	 * 
	 * // 전체 글 개수 int boardListCnt = boardService.boardListCnt();
	 * 
	 * // 페이징 객체 Paging paging = new Paging(); paging.setCri(cri);
	 * paging.setTotalCount(boardListCnt);
	 * 
	 * List<Map<String, Object>> list = boardService.boardList(cri);
	 * 
	 * model.addAttribute("list", list); model.addAttribute("paging", paging);
	 * 
	 * return "board/boardList"; }
	 */
	
}
