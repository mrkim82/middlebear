package com.groo.bear.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groo.bear.mypage.service.CarService;
import com.groo.bear.mypage.service.CarVO;
import com.groo.bear.mypage.service.UserService;
import com.groo.bear.paging.Paging;

@Controller
public class CarController {

	@Autowired
	CarService carService;
	
	@Autowired
	UserService userService;
	
	
	//개인 차량 페이지 조회
	@GetMapping("/carList")
	public String getCarList(HttpServletRequest request, Model model, CarVO carVO) {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		System.out.println(id);
		String empG = userService.checkGrade(id).getEmpGrade();
		System.out.println(empG);
		
		//int carListCnt = carService.carCnt(cri,carVO);
		
		Paging paging = new Paging();
		//paging.setCri(cri);
        //paging.setTotalCount(carListCnt);    
        System.out.println(paging);
        
		if(empG.equals("A")) {
			
			//model.addAttribute("carList",carService.getAllCarList(cri, carVO));
			System.out.println(model);
		    model.addAttribute("paging",paging);
		    System.out.println(model);
			return "car/carList";
		}else {
			model.addAttribute("info",carService.getMyCarInfo(id));
			System.out.println(model);
			model.addAttribute("carPList",carService.getMyCarList(id));
			System.out.println(model);
			return "car/carPList";
		}
		
	}
	

	//개인차량 등록 
	
	@PostMapping("carInsert")
	public String addCar(Model model, CarVO carVO) {
		carService.addCar(carVO);
		return "redirect:carList";
	}
	
	
	//차량수정 
	@PostMapping("carUpdate")
	public String carUpdate(Model model, CarVO carVO) {
		carService.carUpdate(carVO);
		return "redirect:carList";
	}
	
	//삭제
	@PostMapping("carDelete")
	public String carDelete(Model model, String carNo) {
		carService.carDelete(carNo);
		return "redirect:carList";
	}
	
	
	
	
	
	
}
