package com.groo.bear.pro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProVO;

@Controller
public class ProController {
	
	@Autowired
	ProService proService;
	
	//프로젝트 메인 페이지 이동
	@GetMapping("proMain")
	public String proMainPage(Model model) {
		return "pro/proMain";
	}
	
	//프로젝트 생성 페이지 이동
	@GetMapping("proCreate")
	public String 프로젝트생성페이지(Model model) {
		model.addAttribute("proVO", new ProVO());
		return "pro/proCreate";
	}
	
//	//프로젝트 생성
//	@PostMapping("proCreate")
//	public String 프로젝트생성(ProVO proVO) {
//		proService.createProject(proVO);
//		return "redirect:proMain";
//	}
	
	//프로젝트 생성
	@PostMapping("proCreate")
	public String 프로젝트생성(ProVO proVO) {
		//7.09고유키 오류 뜬 적 있음
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("v_pro_name", proVO.getProName());
		param.put("v_pro_content", proVO.getProContent());
		param.put("v_pro_def_tab", proVO.getProDefTab());
		param.put("v_post_write_auth", proVO.getPostWriteAuth());
		param.put("v_post_update_auth", proVO.getPostUpdateAuth());
		param.put("v_post_view_auth", proVO.getPostViewAuth());
		param.put("v_com_write_auth", proVO.getComWriteAuth());
		param.put("v_file_auth", proVO.getFileAuth());
		param.put("v_id", "phantom88");
		
		proService.insertPro(param);
		System.out.println("param 결과 : " + param.toString());
		return "redirect:proMain";
	}
}
