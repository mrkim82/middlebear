package com.groo.bear.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groo.bear.car.service.CarService;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
