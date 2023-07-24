package com.groo.bear.pro.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.pro.service.ProTodoNVoteService;
import com.groo.bear.pro.service.todovote.ProPostTodoVO;

@RestController
public class ProPostTodoController {
	@Autowired
	ProTodoNVoteService ps;
	
	//프로젝트 그룹 수정
	@PutMapping("updateTotoStatus")
	public Map<String, Object> updateTotoStatus(@RequestBody ProPostTodoVO vo) {
		int result = ps.updateTotoStatus(vo);
		
		return Collections.singletonMap("result", result>0?"성공":"취소");
	}
}
