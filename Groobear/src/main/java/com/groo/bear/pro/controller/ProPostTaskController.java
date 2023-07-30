package com.groo.bear.pro.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.pro.service.ProPostTaskService;
import com.groo.bear.pro.service.task.ProPostTaskWorkGroupVO;
import com.groo.bear.pro.service.task.ProUpWorkVo;

@RestController
public class ProPostTaskController {
	@Autowired
	ProPostTaskService task;
	
	@PostMapping("creWorkGroup")
	public Map<String, Object> 업무그룹생성(@RequestBody ProPostTaskWorkGroupVO vo) {
		int result = task.createWorkGroup(vo);
		return Collections.singletonMap("result", result > 0 ? vo.getWorkGroupNo() : "취소");
	}
	
	@PostMapping("delWorkGroup")
	public Map<String, Object> 업무그룹삭제(@RequestBody int workGroupNo) {
		int result = task.deleteWorkGroup(workGroupNo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	@PostMapping("readDetailWorkMem")
	public List<String> 해당work담당자조회(@RequestBody int proPostNo) {
		return task.readDetailWorkPerson(proPostNo);
	}
	
	@PostMapping("upWork")
	public Map<String, Object> upWork(@RequestBody ProUpWorkVo vo) {
		int result = task.updateWorkPost(vo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	
}
