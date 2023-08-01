package com.groo.bear.introducecompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroduceCompanyController {
	
	@GetMapping("/intro/companyintroduce")
	public String getIntroduce() {
		return "intro/companyintroduce";
	}
	
	@GetMapping("/intro/rightpeople")
	public String getRightPeople() {
		return "intro/rightpeople";
	}
}
