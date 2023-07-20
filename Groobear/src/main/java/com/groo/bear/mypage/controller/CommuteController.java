package com.groo.bear.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
			//model.addAttribute("info",commuteService.getMyCarInfo(id));
			System.out.println(model);
			//model.addAttribute("commutePList",commuteService.getMyCarList(id));
			System.out.println(model);
			return null;
		}
		
	}
	
	
	
//
//	//개인차량 등록 
//	
//	@PostMapping("carInsert")
//	public String addCar(Model model, CarVO carVO) {
//		carService.addCar(carVO);
//		return "redirect:carList";
//	}
//	
//	
//	//차량수정 
//	@PostMapping("carUpdate")
//	public String carUpdate(Model model, CarVO carVO) {
//		carService.carUpdate(carVO);
//		return "redirect:carList";
//	}
//	
//	//삭제
//	@ResponseBody
//	@PostMapping("carDelete")
//	public String carDelete(@RequestBody CarVO carVO) {
//		System.out.println(carVO.getCarNo());
//		String result = "";
//		if(carService.carDelete(carVO.getCarNo())>0) {
//			result = "true";
//		} else {
//			result = "false";
//		}
//		
//		return result;
//	}
//	
//	
	
	
}
