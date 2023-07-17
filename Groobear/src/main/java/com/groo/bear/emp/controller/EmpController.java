package com.groo.bear.emp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.groo.bear.emp.service.EmpService;
import com.groo.bear.emp.service.EmpVO;

@Controller
public class EmpController {
	@Autowired
	EmpService empService;
	
/*	@GetMapping("login")
	public String login(HttpServletRequest request, EmpVO empVO) {
		HttpSession session = request.getSession();
		if(empService.login(empVO) != null) {
			session.setAttribute("Id", empService.login(empVO).getId());
			session.setAttribute("Name", empService.login(empVO).getName());
			session.setAttribute("Rank", empService.login(empVO).getRank());
			session.setAttribute("Pno", empService.login(empVO).getPno());
			session.setAttribute("DeptNo", empService.login(empVO).getDeptNo());
			session.setAttribute("Email", empService.login(empVO).getEmail());
			session.setAttribute("Phone", empService.login(empVO).getPhone());
			session.setAttribute("Addr", empService.login(empVO).getAddr());
			session.setAttribute("EmpGrade", empService.login(empVO).getEmpGrade());
			session.setAttribute("Sign", empService.login(empVO).getSign());
			session.setAttribute("ProfileImg", empService.login(empVO).getProfileImg());
			session.setAttribute("ProfileNote", empService.login(empVO).getProfileNote());		
			return "main/main";
		}else {
			return "../static/index";
		}
	}*/
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false); 
        if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
}
