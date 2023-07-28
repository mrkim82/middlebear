package com.groo.bear.pro.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.pro.service.ProPostTaskService;
import com.groo.bear.pro.service.task.ProPostTaskWorkGroupVO;

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
}
