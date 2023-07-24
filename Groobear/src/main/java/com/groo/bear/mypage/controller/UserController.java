package com.groo.bear.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	// 마이페이지 입장 전 패스워드 확인 페이지 이동
	@GetMapping("myPageCheckPassword")
	public String myPageCheckPassword() {
		return "mypage/myPageCheckPw";
	}
	// 회원정보 페이지 이동(암호확인)
	@GetMapping("myPageInfo")
	public String myPageChangeInfo(@RequestParam String password, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		EmpVO vo = new EmpVO();
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder(); 
		vo.setId(id);
		EmpVO checkVo = userService.checkScPw(id);
		if(scpwd.matches(password, checkVo.getPassword())) {
			vo.setPassword(checkVo.getPassword());
			model.addAttribute("userInfo", userService.checkMypage(vo));
			model.addAttribute("pwd", password);
			model.addAttribute("profImg", userService.checkMyProf(id));
			return "mypage/myPageInfo";
		}else {
			return "mypage/myPageCheckPw";
		}	
	}
	
	// 회원정보 변경
	@PostMapping("changeUserInfo")
	@ResponseBody
	public String changeUser(@RequestBody EmpVO vo) {
		userService.deleteProfImg(vo.getId());
		if(vo.getUuid() != null && vo.getUuid() != "") {
			userService.insertProfImg(vo);
		}
		if(userService.updateUsers(vo) > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
}
