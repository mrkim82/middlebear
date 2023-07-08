package com.groo.bear.pro.controller;

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
		System.out.println(model);
		return "pro/proCreate";
	}
	
	//프로젝트 생성
	@PostMapping("proCreate")
	public String 프로젝트생성(ProVO proVO) {
		proService.createProject(proVO);
		return "redirect:proMain";
	}
}
