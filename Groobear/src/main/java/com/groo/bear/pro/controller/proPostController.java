package com.groo.bear.pro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.pro.service.ProPostService;
import com.groo.bear.pro.service.ProPostVO;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProVO;

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
	
	@GetMapping("proPostMain/{proMemNo}")
	public String proPostPage(Model model, HttpServletRequest request, @PathVariable int proMemNo, ProPostVO vo) {
		String pagePath ="";
		int homeTab = Integer.parseInt(vo.getHomeTab());
		
		proData2(model, request);
		
		model.addAttribute("projectTopBar", proPostService.readTopMenu(proMemNo));
		model.addAttribute("projectUserCount", proPostService.readTopMenuCount(proMemNo));
		System.out.println("오류"+model);
		switch (homeTab) {
		//피드
		case 1 :
			
			pagePath = "proPost/proPostDetail";
			break;
		//업무
		case 2 :
			
			pagePath = "proPost/proPostTask";
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
