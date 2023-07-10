package com.groo.bear.emp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groo.bear.emp.service.EmpService;
import com.groo.bear.emp.service.EmpVO;

@Controller
public class EmpController {
	@Autowired
	EmpService empService;
	
	@GetMapping("login")
	public String login(HttpServletRequest request, EmpVO empVO, Model model) {
		HttpSession session = request.getSession();
		System.out.println(empVO);
		System.out.println(empService.login(empVO));
		if(empService.login(empVO) != null) {
			session.setAttribute("UserId", empVO.getId());
			return "main/main";
		}else {
			return null;
		}
		
	}
}
