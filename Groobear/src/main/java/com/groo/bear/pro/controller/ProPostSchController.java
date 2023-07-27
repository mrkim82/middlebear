package com.groo.bear.pro.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.schvo.ProCreateSchVO;
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
		
		vo.setId((String)session.getAttribute("Id"));
		int result = ppss.updateSchPartiCheck(vo);
		
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//참석자 단건 여러번 추가
	@PostMapping("insertPartiMemberAll")
	@ResponseBody
	public Map<String, Integer> insertPartiMemberAll(HttpServletRequest request, @RequestBody List<ProPostSchVO> list) {
		Map <String, Integer> map = new HashMap<>();
		
		map.put("result", ppss.insertPartiMemberAll(list));//성공 건수 반환
		
		return map;
	}
	
	//개인 캘린더 조회페이지 이동
	@GetMapping("perSch")
	public String proMainPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		
		model.addAttribute("readPersonalSch", ppss.readPersonalSch(id));
		model.addAttribute("readPerCalDetail", ppss.readPerCalDetail(id));
		model.addAttribute("readPerCalCom", ppss.readPerCalCom(id));
		
		return "main/personalSch";
	};
	
	//업무 생성
	@PostMapping("creSch")
	@ResponseBody
	public Map<String, Object> creSch(HttpServletRequest request, @RequestBody ProCreateSchVO vo) {
		HttpSession session = request.getSession();
		Map <String, Object> map = new HashMap<>();
		vo.setId((String)session.getAttribute("Id"));
		
		ppss.createPostSch(vo);
		
		map.put("result", "성공했겠지");
		return map;
	}
	
}
