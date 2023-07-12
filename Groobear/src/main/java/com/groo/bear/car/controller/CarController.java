package com.groo.bear.car.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groo.bear.car.service.CarService;
import com.groo.bear.car.service.CarVO;

@Controller
public class CarController {

	@Autowired
	CarService carService;
	
	//전체조회 페이지
	@GetMapping("carList")
	public String carList(Model model) {
		model.addAttribute("carList",carService.getAllCarList());
		return "car/carList";
	}
	
	//개인 차량 페이지 조회
	@GetMapping("carPList")
	public String carPList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		model.addAttribute("carPList",carService.getMyCarList(id));
		//System.out.println(model);
		return "car/carPList";
	}
	
	//개인차량 등록 
	
	
	
	
	
	
	
	
	
	
	
	
}
