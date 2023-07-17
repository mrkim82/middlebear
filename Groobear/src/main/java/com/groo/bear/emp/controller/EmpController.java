package com.groo.bear.emp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.groo.bear.sms.service.MessageDTO;
import com.groo.bear.sms.service.SmsResponseDTO;
import com.groo.bear.sms.service.SmsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmpController {
	@Autowired
	EmpService empService;
	
	private final SmsService smsService;
	
	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false); 
        if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	// SMS 인증번호 발송
	@PostMapping("checkSms")
	@ResponseBody
	public SmsResponseDTO sendSms(@RequestBody MessageDTO messageDto) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		SmsResponseDTO response = smsService.sendSms(messageDto);
		System.out.println("SMS 정보");
		System.out.println(response);
		return response;
	}
	
	// 사원정보조회(이름 & 전화번호를 이용한 정보 일치여부 비교)
	@PostMapping("checkEmp")
	@ResponseBody
	public EmpVO checkEmp(@RequestBody EmpVO vo) {
		System.out.println("EMP 이름, 전화번호");
		System.out.println(vo.getName()+" / "+vo.getPhone());
		vo = empService.getEmpInfo(vo);
		System.out.println("EMP 조회 정보");
		System.out.println(vo);
		return vo;
	}
		
}
