package com.groo.bear.pro.controller;

import java.util.Collections;
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
import com.groo.bear.pro.service.PublicCodeService;
import com.groo.bear.pro.service.PublicCodeVO;

@RestController
public class PublicCodeColorController {
	
	@Autowired
	PublicCodeService pccs;
	
	//프로젝트 멤버 색상 수정
	@PutMapping("updateProMemColor")
	public Map<String, Object> updateWorkPostStatus(@RequestBody ProGroupVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setId((String)session.getAttribute("Id"));
		
		int result = pccs.updateProMemColor(vo);
		
		return Collections.singletonMap("result", result>0?"성공":"취소");
	}
}
