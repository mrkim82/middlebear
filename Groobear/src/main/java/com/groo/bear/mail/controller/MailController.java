package com.groo.bear.mail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groo.bear.mail.service.EmailService;
import com.groo.bear.mail.service.MailService;
import com.groo.bear.mail.service.MailVO;

@Controller
public class MailController {
	@Autowired
	EmailService emailService;
	@Autowired
	MailService mailService;
	
	//받은메일함
	@GetMapping("mail/receiveMail")
	public String receiveMailForm() {
		return "mail/receiveMail";
	}
	//메일작성폼
	@GetMapping("mail/sendMail")
	public String sendMailForm() {
		return "mail/sendMail";
	}
	//메일 작성(발송)
	@PostMapping("sendingMail")
	public String sendMail(Model model, MailVO mailVO,HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("1번");
		System.out.println(mailVO);
		System.out.println(session.getAttribute("Id"));
		String id = (String) session.getAttribute("Id");
		mailVO.setId(id);
		System.out.println(mailVO);
		model.addAttribute("mail",mailService.sendMail(mailVO));
		emailService.sendSimpleMessage(mailVO);
        System.out.println("메일 전송 완료");
		
		return "mail/sendingMail";
	}
	//보낸메일함
	@GetMapping("mail/sendingMail")
	public String sendingMailForm() {
		return "mail/sendingMail";
	}
	//지운메일함
	@GetMapping("mail/deleteMail")
	public String deleteMailForm() {
		return "mail/deleteMail";
	}
	//완전삭제
}
