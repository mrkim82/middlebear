package com.groo.bear.mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {
	
	//받은메일함
	@GetMapping("mail/receiveMail")
	public String receiveMailForm() {
		return "mail/receiveMail";
	}
	//메일작성
	
	//보낸메일함
	
	//지운메일함
	
	//완전삭제
}
