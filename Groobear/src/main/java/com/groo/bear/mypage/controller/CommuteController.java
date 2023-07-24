package com.groo.bear.mypage.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.mypage.service.CommuteService;
import com.groo.bear.mypage.service.CommuteVO;
import com.groo.bear.mypage.service.UserService;
import com.groo.bear.paging.Criteria;
import com.groo.bear.paging.Paging;

@Controller
public class CommuteController {

	@Autowired
	CommuteService commuteService;
	
	@Autowired
	UserService userService;
	
	
	//근태 페이지 조회
	@GetMapping("/commuteList")
	public String getCarList(HttpServletRequest request, Criteria cri, Model model, CommuteVO commuteVO) {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		System.out.println(id);
		String empG = userService.checkGrade(id).getEmpGrade();
		System.out.println(empG);
		
		int commuteListCnt = commuteService.commuteCnt(cri, commuteVO);
		
		Paging paging = new Paging();
		paging.setCri(cri);
        paging.setTotalCount(commuteListCnt);    
        System.out.println(paging);
        
		if(empG.equals("A")) {
			model.addAttribute("commuteList",commuteService.getAllCommuteList(cri, commuteVO));
			System.out.println(model);
			
			model.addAttribute("paging",paging);
		    //System.out.println(model);
			return "commute/commuteA";
		}else {
			model.addAttribute("info",commuteService.getMyCommuteList(cri, id));
			model.addAttribute("paging",paging);
			System.out.println(model);
			
			return "commute/commuteP";
		}
		
	}
	
	//출퇴근 기록 조회
	@ResponseBody
	@PostMapping("getWork")
	public CommuteVO getWork(@RequestBody CommuteVO vo) {
		System.out.println("1111"+vo.getId());
		vo = (CommuteVO) commuteService.chkWork(vo.getId());
		System.out.println(vo);
		return vo;
	}
	
	//출근 등록 
	@ResponseBody
	@PostMapping("workStart")
	public CommuteVO workStart(@RequestBody CommuteVO commuteVO) {
		
		System.out.println(commuteVO);
		commuteService.startWork(commuteVO);
		return commuteVO;
	}
	
	
	//퇴근등록
	@ResponseBody
	@PostMapping("workEnd")
	public CommuteVO workEnd(@RequestBody CommuteVO commuteVO) {
		commuteService.endWork(commuteVO);
		return commuteVO;
	}
	
	//연장근무 시작
	@ResponseBody
	@PostMapping("overWorkStart")
	public CommuteVO overWorkStart(@RequestBody CommuteVO commuteVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Date workEnd = (Date)session.getAttribute("WorkEnd");
		System.out.println(workEnd);
		if(workEnd != null) {
			commuteService.startOverWork(commuteVO);	
		}else {
			commuteService.ewStartOverWork(commuteVO);
			commuteService.startOverWork(commuteVO);
		}
		
		return commuteVO;
	}
	
	//연장근무 종료
	@ResponseBody
	@PostMapping("overWorkEnd")
	public CommuteVO overWorkEnd(@RequestBody CommuteVO commuteVO) {
		commuteService.endOverWork(commuteVO);
		return commuteVO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
