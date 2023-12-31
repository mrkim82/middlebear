package com.groo.bear.pro.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.pro.service.ProTodoNVoteService;
import com.groo.bear.pro.service.todovote.CreateVoteVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreVO;
import com.groo.bear.pro.service.todovote.ProPostTodoVO;
import com.groo.bear.pro.service.todovote.ProTodoDetailVO;
import com.groo.bear.pro.service.todovote.ProUpdateTodoVO;
import com.groo.bear.pro.service.todovote.ProUpdateVoteVO;
import com.groo.bear.pro.service.todovote.ProVoteCDVO;

@RestController
public class ProPostTodoNVoteController {
	@Autowired
	ProTodoNVoteService ps;
	
	//프로젝트 그룹 수정
	@PutMapping("updateTotoStatus")
	public Map<String, Object> updateTotoStatus(@RequestBody ProPostTodoVO vo) {
		int result = ps.updateTotoStatus(vo);
		
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//todo게시글 작성
	@PostMapping("creTodoPost")
	public Map<String, Object> creTodoPost(HttpServletRequest request, @RequestBody ProPostTodoCreVO vo) {
		Map <String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		
		vo.setId((String)session.getAttribute("Id"));
		
		int result = ps.createTodo(vo);
		map.put("result", result);
		return map;
	}
	
	//vote게시글 작성
	@PostMapping("creVotePost")
	public Map<String, Object> creVotePost(HttpServletRequest request, @RequestBody CreateVoteVO vo) {
		Map <String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		vo.setId((String)session.getAttribute("Id"));
		
		System.out.println(vo);
		ps.createPostVote(vo);
		map.put("result", "잘 들어가겠지");
		return map;
	}
	
	//프로젝트 그룹 수정
	@PutMapping("updateVoteStatus")
	public Map<String, Object> updateVoteStatus(@RequestBody int changeVoteStatusNo) {
		int result = ps.updateVoteStatus(changeVoteStatusNo);
		
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	
	//투표하기
	@PostMapping("insertVote")
	public Map<String, Object> insertVote(HttpServletRequest request, @RequestBody ProVoteCDVO vo) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		vo.setId(id);
		int result = ps.insertVote(vo);
		System.out.println("님"+vo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//투표취소
	@PostMapping("deleteVote")
	public Map<String, Object> deleteVote(HttpServletRequest request, @RequestBody ProVoteCDVO vo) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		vo.setId(id);
		int result = ps.deleteVote(vo);
		
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//투표수정
	@PostMapping("upVote")
	public Map<String, Object> upVote(@RequestBody ProUpdateVoteVO vo) {
		int result = 0;
		ps.updateVotePost(vo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//투표취소
	@PostMapping("deVote")
	public List<ProTodoDetailVO> deVote(@RequestBody int proPostNo) {
		return ps.readTodoDetail(proPostNo);
	}
	
	//할 일 수정
	@PostMapping("upTodo")
	public Map<String, Object> upTodo(@RequestBody List<ProUpdateTodoVO> vo) {
		int result = ps.updateTodo(vo);
		System.out.println(result);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//할일 단건 삭제
	@DeleteMapping("delDetailTodo")
	public Map<String, Object> delDetailTodo(@RequestBody int todoNo) {
		int result = ps.deleteTodoDetail(todoNo);
		
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	
}
