package com.groo.bear.pro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.groo.bear.pro.service.ProPostService;
import com.groo.bear.pro.service.ProPostUserVO;
import com.groo.bear.pro.service.ProPostVO;
import com.groo.bear.pro.service.ProService;

@Controller
public class proPostController {

	@Autowired
	ProService proService;
	
	@Autowired
	ProPostService proPostService;
	
	//공통 데이터(사이드바) 전달
	private Model proData2(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		model.addAttribute("projectGroupList", proService.readProjectGroup((String)session.getAttribute("Id")));
		model.addAttribute("projectPartiList", proService.readProjectParti((String)session.getAttribute("Id")));
		
		return model;
	}
	
	@GetMapping("proPostMain/{proNo}")
	public String proPostPage(Model model, HttpServletRequest request, @PathVariable int proNo, ProPostVO vo, ProPostUserVO vo2) {
		HttpSession session = request.getSession();
		String pagePath ="";
		int homeTab = Integer.parseInt(vo.getHomeTab());
		String id = (String)session.getAttribute("Id");
		
		proData2(model, request);
		
		model.addAttribute("projectTopBar", proPostService.readTopMenu(proNo, id));//메뉴 상단바 조회
		model.addAttribute("projectUserCount", proPostService.readTopMenuCount(id, proNo));
		model.addAttribute("projectPartiMember", proPostService.readProjectParti(vo2));//회원 정보 전체 조회
		
		switch (homeTab) {
		//피드
		case 1 :
			
			pagePath = "proPost/proPostTask";
			break;
		//업무
		case 2 :
			
			pagePath = "proPost/proPostDetail";
			break;
		//간트차트
		case 3 :
			
			pagePath = "proPost/proPostChart";
			break;
		//캘린더
		case 4 :
			
			pagePath = "proPost/proPostSchd";
			break;
		//파일
		case 5 :
			
			pagePath = "proPost/proPostFile";
			break;
		//알림
		case 6 :
			
			pagePath = "proPost/proPostAlarm";
			break;
		//에러
		default:
			pagePath = "proPost/proPostError";
			break;
		}
		
		
		return pagePath;
	}
	
}