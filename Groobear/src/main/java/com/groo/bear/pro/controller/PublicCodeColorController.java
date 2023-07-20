package com.groo.bear.pro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.pro.service.ProGroupVO;
import com.groo.bear.pro.service.PublicCodeColorService;
import com.groo.bear.pro.service.PublicCodeColorVO;

@RestController
public class PublicCodeColorController {
	
	@Autowired
	PublicCodeColorService pccs;
	
	//프로젝트 멤버 색상 수정
	@PutMapping("updateProMemColor")
	public Map<String, Object> updateWorkPostStatus(@RequestBody ProGroupVO vo, HttpServletRequest request) {
		Map <String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		String res;
		
		vo.setId((String)session.getAttribute("Id"));
		
		int result = pccs.updateProMemColor(vo);
		
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		map.put("result", res);
		System.out.println(vo);
		return map;
	}
}
