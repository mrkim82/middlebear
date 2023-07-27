package com.groo.bear.pro.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.pro.service.ProTodoNVoteService;
import com.groo.bear.pro.service.todovote.CreateVoteVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreVO;
import com.groo.bear.pro.service.todovote.ProPostTodoVO;

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
}
