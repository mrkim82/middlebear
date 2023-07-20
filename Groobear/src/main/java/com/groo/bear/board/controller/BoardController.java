package com.groo.bear.board.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groo.bear.board.service.BoardService;
import com.groo.bear.board.service.BoardVO;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.paging.Criteria;
import com.groo.bear.paging.Paging;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BoardController {
	
	//ㄴㅁㅇㅁㄴㅁㅇㄴ
	@Autowired
	BoardService boardService;
	
	@GetMapping("/boardList")
	public String getboardList(Criteria cri, Model model, BoardVO boardVO) {
		System.out.println(cri);
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
	
	@GetMapping("boardInfo")
	public String getBoard(Model model, @RequestParam int boardNo) {
		model.addAttribute("board", boardService.selectBoard(boardNo));
		model.addAttribute("boardCom", boardService.readBoardComment(boardNo));
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
	public String boardInsert(@ModelAttribute BoardVO boardVO, Model model, RedirectAttributes rttr) {
		log.info("===============================");
		log.info("register : " + boardVO);
		boardService.insertBoard(boardVO);
		
//		if(boardVO.getAttachList() != null) {
//			System.out.println(boardVO.getAttachList());
//			boardVO.getAttachList().forEach(attach -> log.info(attach));
//		}
		
		log.info("===============================");
		rttr.addFlashAttribute("result", boardVO.getBoardNo());
		return "redirect:/boardList?boardType=" + boardVO.getBoardType();
	}
	
	@GetMapping("boardUpdate/{boardNo}")
	public String getUpdatePage(@PathVariable("boardNo") int boardNo, Model model) {
	    BoardVO board = boardService.selectBoard(boardNo);
	    model.addAttribute("board", board);
	    return "board/boardUpdate";
	}
	//게시글수정
	@PostMapping("boardUpdate")
	public String boardUpdate(BoardVO boardVO, Model model) {
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
	
	private void deleteFiles(List<FilesVO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		log.info("delete attach files ......");
		log.info(attachList);
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\" + attach.getUuid()+"_"+attach.getFileName());
				Files.deleteIfExists(file);
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_" + attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumbNail);
				}
			} catch(Exception e) {
				log.error("delete file error" + e.getMessage());
			} //end catch
		}); //end foreachd
	}
	//RequestBody -> 화면에서 객체 타입을 받을 때, RequestParam -> 화면에서 하나의 타입을 받을 때 
	@PostMapping("/remove")
	public String remove(@RequestParam("boardNo") int boardNo, Criteria cri, RedirectAttributes rttr) {
		log.info("remove ............. " + boardNo);
		List<FilesVO> attachList = boardService.getAttachList(boardNo);
		if(boardService.remove(boardNo)) {
			//delete Attach Files
			deleteFiles(attachList);
			
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/boardList"+ cri.getListLink();
	}
}
