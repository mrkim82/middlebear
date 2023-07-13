package com.groo.bear.board.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;

public class BoardTest {
	
	@Autowired
	BoardService boardService;
	
//	@Test
//	public void boardList(Model model) {
//		model.addAttribute("boardList", boardService.selectAllList());
//		System.out.println("성공");
//	}
	
}
