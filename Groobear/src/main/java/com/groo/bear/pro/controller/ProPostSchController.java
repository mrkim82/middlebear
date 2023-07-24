package com.groo.bear.pro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.schvo.ProPostSchVO;

@RestController
public class ProPostSchController {
	@Autowired
	ProPostSchService ppss;
	
	//참석여부변경
	@PutMapping("updateSchPartiCheck")
	public Map<String, Object> updateSchPartiCheck(HttpServletRequest request, @RequestBody ProPostSchVO vo) {
		HttpSession session = request.getSession();
		Map <String, Object> map = new HashMap<>();
		String res;
		
		vo.setId((String)session.getAttribute("Id"));
		int result = ppss.updateSchPartiCheck(vo);
		
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		
		map.put("result", res);
		return map;
	}
	
	//참석자 단건 여러번 추가
	@PostMapping("insertPartiMemberAll")
	public Map<String, Integer> insertPartiMemberAll(HttpServletRequest request, @RequestBody List<ProPostSchVO> list) {
		Map <String, Integer> map = new HashMap<>();
		
		int result = ppss.insertPartiMemberAll(list);
		
		map.put("result", result);
		return map;
	}
	
	//참석자 전체 삭제
	@PostMapping("deletePartiMemberAll")
	public String deletePartiMemberAll(HttpServletRequest request, @RequestBody int schPlNo) {
		ppss.deletePartiMemberAll(schPlNo);
		return "삭제";
	}
	
}
