package com.groo.bear.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class CarController {

	@Autowired
	CarService carService;
	
	//전체조회 페이지
	@GetMapping("carList")
	public String carList(Model model) {
		model.addAttribute("carList",carService.getCarList());
		return "car/carList";
	}
}
