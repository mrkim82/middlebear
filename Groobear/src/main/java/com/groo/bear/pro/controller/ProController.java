package com.groo.bear.pro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProVO;

@Controller
public class ProController {
	
	@Autowired
	ProService proService;
	
	//프로젝트 메인 페이지 이동
	@GetMapping("proMain")
	public String proMainPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProject((String)session.getAttribute("Id")));
		model.addAttribute("projectGroupList", proService.readProjectGroup((String)session.getAttribute("Id")));
		System.out.println("메인"+model);
		return "pro/proMain";
	}
	
	//프로젝트 메인 페이지 숨김 보기
	@GetMapping("proMainH")
	public String proMainPageH(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProjectHide((String)session.getAttribute("Id")));
		return "pro/proMainNormal";
	}
	
	//프로젝트 메인 페이지 즐찾 보기
	@GetMapping("proMainS")
	public String proMainPageS(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProjectStar((String)session.getAttribute("Id")));
		return "pro/proMainNormal";
	}
	
	//프로젝트 생성 페이지 이동
	@GetMapping("proCreate")
	public String 프로젝트생성페이지(Model model) {
		model.addAttribute("proVO", new ProVO());//생성 수정 관리, 페이지에서 field값 수정
		System.out.println(model);
		return "pro/proCreate";
	}
	
	//프로젝트 생성
	@PostMapping("proCreate")
	public String 프로젝트생성(ProVO proVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
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
		param.put("v_id", (String)session.getAttribute("Id"));
		
		proService.insertPro(param);
		System.out.println("param 결과 : " + param.toString());
		return "redirect:proMain";
	}
	
//	//프로젝트 즐겨찾기
	@PostMapping("proUpdate")
	public String starCheck(@RequestParam("pMN") int pMN, @RequestParam("starC") String starC) {
		String res;
		if(starC.equals("N")) {
			//프로젝트 즐겨찾기 등록
			proService.updateStarY(pMN);
			res = "등록";
		} else{
			//프로젝트 즐겨찾기 취소
			proService.updateStarN(pMN);
			res = "취소";
		}
		return res;
	}
}
