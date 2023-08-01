package com.groo.bear.emp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.groo.bear.emp.service.EmpService;
import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.mypage.service.CommuteService;
import com.groo.bear.mypage.service.OffService;
import com.groo.bear.mypage.service.OffVO;
import com.groo.bear.mypage.service.UserService;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.sms.service.MessageDTO;
import com.groo.bear.sms.service.SmsResponseDTO;
import com.groo.bear.sms.service.SmsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmpController {
	@Autowired
	EmpService empService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OffService offService;
	
	@Autowired
	ProService proService;
	
	@Autowired
	CommuteService commuteService;
	
	private final SmsService smsService;
	
	// SMS 인증번호 발송
	@PostMapping("checkSms")
	@ResponseBody
	public SmsResponseDTO sendSms(@RequestBody MessageDTO messageDto) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		SmsResponseDTO response = smsService.sendSms(messageDto);
		return response;
	}
	
	// 사원정보조회(이름 & 전화번호 / 아이디 & 전화번호를 이용한 정보 일치여부 비교)
	@PostMapping("checkEmp")
	@ResponseBody
	public EmpVO checkEmp(@RequestBody EmpVO vo) {
		vo = empService.getEmpInfo(vo);
		return vo;
	}
	
	// 비밀번호 변경 화면 이동
	@GetMapping("changePw")
	public String changePwForm(@RequestParam String id, Model model) {
		System.out.println(id);
		model.addAttribute("empInfo", id);
		return "main/changePw";
	}
	
	// 비밀번호 변경 & 암호화
	@PostMapping("changePw")
	@ResponseBody
	public String changePw(@RequestBody EmpVO vo) {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder(); 
		String password = scpwd.encode(vo.getPassword());
		vo.setPassword(password);
		String result = "";
		if(empService.changePw(vo) > 0) {
			result = "success";
			return result;
		} else {
			result = "fail";
			return result;
		}
	}
	
	// 인사정보 조회(이름을 이용한 인사정보 조회/회원가입)
	@GetMapping("signUpInfo")
	public String searchEmpInfo(@RequestParam String name, Model model) {
		model.addAttribute("empInfo", empService.searchEmpInfo(name));
		return "main/signUpInfo";
	}
	
	// 회원가입 아이디 중복체크
	@ResponseBody
	@GetMapping("checkId")
	public String checkId(@RequestParam String id) {
		System.out.println(id);
		String result = "";
		if(empService.checkId(id)>0) {
			result = "X";
		}else {
			result = "O";
		}
		return result;
	}
	
	// 회원가입 연락처 중복체크
	@ResponseBody
	@GetMapping("checkPhone")
	public String checkPhone(@RequestParam String phone) {
		System.out.println(phone);
		String result = "";
		if(empService.checkPhone(phone)>0) {
			result = "X";
		}else {
			result = "O";
		}
		return result;
	}
	
	// 회원가입
	@PostMapping("empSignUp")
	@ResponseBody
	public String signUp(@RequestBody EmpVO vo) {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder(); 
		System.out.println("기존 비밀번호 : "+vo.getPassword());
		String password = scpwd.encode(vo.getPassword());
		System.out.println("암호화 비밀번호 : "+password);
		vo.setPassword(password);
		OffVO offvo = new OffVO();
		offvo.setId(vo.getId());
		
		
		
		String result = "";
		if(empService.signUp(vo) > 0) {
			offService.firstSetOff(offvo);
			proService.createProGroup(vo.getId());
			commuteService.settingDay(vo.getId());
			result = "success";
			return result;
		} else {
			result = "fail";
			return result;
		}
	}
	// 메인 프로필 사진
	@ResponseBody
	@GetMapping("checkProfImg")
	public EmpVO mainCheckProfImg(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("Id");
		EmpVO vo = userService.checkMyProf(id);
		return vo;
	}
}
