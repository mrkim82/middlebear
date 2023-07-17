package com.groo.bear.mail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groo.bear.mail.service.MailService;
import com.groo.bear.mail.service.MailVO;

@Controller
public class MailController {
	
	@Autowired
	MailService mailService;
	
	//받은메일함
	@GetMapping("mail/receiveMail")
	public String receiveMailForm() {
		//메일 지우기
		return "mail/receiveMail";
	}
	//메일작성폼
	@GetMapping("mail/sendMail")
	public String sendMailForm() {
		return "mail/sendMail";
	}
	//메일 작성(발송)
	@PostMapping("sendMail")
	public String sendMail(Model model, MailVO mailVO,HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("1번");
		System.out.println(mailVO);
		System.out.println(session.getAttribute("Id"));
		String id = (String) session.getAttribute("Id");
		mailVO.setId(id);
		System.out.println(mailVO);
		model.addAttribute("mail",mailService.sendMail(mailVO));
        System.out.println("메일 전송 완료");
		return "redirect:mail/sendingMail";
	}
	//보낸메일함
	@GetMapping("mail/sendingMail")
	public String sendingMailForm(Model model, MailVO mailVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("Email");
		model.addAttribute("mailList",mailService.sendingMail(email));
		//메일 지우기
		return "mail/sendingMail";
	}
	//지운메일함 폼
	@GetMapping("mail/deleteMail")
	public String deleteMailForm() {
		return "mail/deleteMail";
	}
	//지운메일함 기능
	
	//메일 상세조회
	@GetMapping("mailInfo")
	public String mailInfo(@RequestParam int mailNo, Model model) {
		model.addAttribute("mail",mailService.mailInfo(mailNo));
		return "mail/mailInfo";
	}
	
}
