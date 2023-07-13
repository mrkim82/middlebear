package com.groo.bear.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groo.bear.mypage.service.CarService;
import com.groo.bear.mypage.service.UserService;

@Controller
public class CarController {

	@Autowired
	CarService carService;
	@Autowired
	UserService userService;
	
	//전체조회 페이지
//	@GetMapping("carList")
//	public String carList(Model model) {
//		model.addAttribute("carList",carService.getAllCarList());
//		return "car/carList";
//	}
	
	//개인 차량 페이지 조회
	@GetMapping("carList")
	public String carList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		System.out.println(id);
		String empG = userService.checkGrade(id).getEmpGrade();
		System.out.println(empG);
		if(empG.equals("A")) {
			model.addAttribute("carList",carService.getAllCarList());
			return "car/carList";
		}else {
			model.addAttribute("carPList",carService.getMyCarList(id));
			return "car/carPList";
		}
		
	}
	
	//개인차량 등록 
	
	
	
	
	
	
	
	
	
	
	
	
}
