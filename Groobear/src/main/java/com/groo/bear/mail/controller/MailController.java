package com.groo.bear.mail.controller;

import java.util.List;

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

import com.groo.bear.mail.service.MailService;
import com.groo.bear.mail.service.MailVO;

@Controller
public class MailController {
	
	@Autowired
	MailService mailService;
	
	//받은메일함
	@GetMapping("mail/receiveMail")
	public String receiveMailForm(Model model, MailVO mailVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("Email");
		model.addAttribute("mailList",mailService.receiveMail(email));
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
		return "mail/sendingMail";
	}
	//지운메일함 폼
	@GetMapping("mail/deleteMail")
	public String deleteMailForm(Model model, MailVO mailVO, HttpServletRequest request) {
		System.out.println("dm = "+mailVO);
		HttpSession session = request.getSession();
		mailVO.setSender((String) session.getAttribute("Email"));
		mailVO.setReceiver((String) session.getAttribute("Email"));
		model.addAttribute("mailList",mailService.deletedMail(mailVO));
		return "mail/deleteMail";
	}
	//보낸메일,받은메일에서 삭제시 update문으로 delete_date,mail_type설정
	@PostMapping("mail/delete")
	@ResponseBody
	public int deleteMail(@RequestBody List<Integer> delList) {
		//update하는곳
		int count=0;
	    for (int i = 0 ; i < delList.size() ; i++) { 
	        mailService.deleteMail(delList.get(i));
	        System.out.println("mailNO : "+delList.get(i));
	        System.out.println("mailNo : "+mailService.deleteMail(delList.get(i)));
	        count++;
	    }
		return count;
	}
	//메일 상세조회
	@GetMapping("mailInfo")
	public String mailInfo(@RequestParam int mailNo, Model model) {
		model.addAttribute("mail",mailService.mailInfo(mailNo));
		return "mail/mailInfo";
	}
	//지운메일함에서 삭제시 완전삭제(db에서 delete)
	@PostMapping("mail/realdelete")
	@ResponseBody
	public int realDeleteMail(@RequestBody List<Integer> delList) {
		//update하는곳
		int count=0;
	    for (int i = 0 ; i < delList.size() ; i++) { 
	    	System.out.println("mailNo : "+mailService.deleteMail(delList.get(i)));
	        mailService.realDeleteMail(delList.get(i));
	        System.out.println("mailNO : "+delList.get(i));
	        count++;
	    }
		return count;
	}
}
