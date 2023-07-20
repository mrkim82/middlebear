package com.groo.bear.pro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.schvo.ProPostSchVO;

@Controller
public class ProPostSchController {
	@Autowired
	ProPostSchService ppss;
	
	//참석여부변경
	@PutMapping("updateSchPartiCheck")
	@ResponseBody
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
}
