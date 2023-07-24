package com.groo.bear.pro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		System.out.println(vo);
		return map;
	}
	
	//참석자 단건 여러번 추가
	@PostMapping("insertPartiMemberAll")
	public Map<String, Integer> insertPartiMemberAll(HttpServletRequest request, @RequestBody List<ProPostSchVO> list) {
		Map <String, Integer> map = new HashMap<>();
		System.out.println("값확인"+list);
		int result = ppss.insertPartiMemberAll(list);
		System.out.println("추가"+result);
		System.out.println("추가vo"+list);
		map.put("result", result);
		return map;
	}
	
	//참석자 전체 삭제
	@PostMapping("deletePartiMemberAll")
	public Map<String, String> deletePartiMemberAll(HttpServletRequest request, @RequestBody int schPlNo) {
		Map <String, String> map = new HashMap<>();
		System.out.println("값확인"+schPlNo);
		int result = ppss.deletePartiMemberAll(schPlNo);
		String res;
		
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		System.out.println("삭제"+ppss);
		System.out.println("삭제no"+schPlNo);
		map.put("result", res);
		return map;
	}
	
	
}
