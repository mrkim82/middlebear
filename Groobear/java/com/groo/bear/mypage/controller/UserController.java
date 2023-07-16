package com.groo.bear.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.mypage.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("mypage")
	public String checkGrade(HttpServletRequest request, EmpVO empVO) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		System.out.println(userService.checkGrade(id).getEmpGrade());
		String empG = userService.checkGrade(id).getEmpGrade();
		
		if(empG.equals("A")) {
			return "mypage/myPageMainA";
		}else {
			return "mypage/myPageMainP";
		}
	}
	
	
	
	
	
}
