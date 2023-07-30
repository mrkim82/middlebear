package com.groo.bear.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.groo.bear.security.service.SecurityVO;


public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("handler 실행");
		HttpSession session = request.getSession();
		SecurityVO vo = (SecurityVO) authentication.getPrincipal();
		System.out.println(vo.getName());

		
		
		if(vo != null) {
			session.setAttribute("EmpNo", vo.getEmpNo());
			session.setAttribute("Id", vo.getId());
			session.setAttribute("Name", vo.getName());
			session.setAttribute("Rank", vo.getRank());
			session.setAttribute("Pno", vo.getPno());
			session.setAttribute("DeptNo", vo.getDeptNo());
			session.setAttribute("Email", vo.getEmail());
			session.setAttribute("Phone", vo.getPhone());
			session.setAttribute("Addr", vo.getAddr());
			session.setAttribute("EmpGrade", vo.getEmpGrade());
			session.setAttribute("Sign", vo.getSign());
			session.setAttribute("ProfileImg", vo.getProfileImg());
			session.setAttribute("ProfileNote", vo.getProfileNote());	
				
		}
		
		
		response.sendRedirect("/main");
	}
}
