package com.groo.bear;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.groo.bear.board.service.BoardService;

@SpringBootTest
class GroobearApplicationTests {
	
	@Autowired
	BoardService boardService;
	
//	@Test
//	public String boardList(Model model) {
//		model.addAttribute("boardList", boardService.selectAllList());
//		return "board/boardList";
//	}
}
