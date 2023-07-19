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
import com.groo.bear.paging.Criteria;
import com.groo.bear.paging.Paging;

@Controller
public class MailController {
	
	@Autowired
	MailService mailService;
	
	//받은메일함
	@GetMapping("mail/receiveMail")
	public String receiveMailForm(Criteria cri ,Model model, MailVO mailVO, HttpServletRequest request,  @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("Email");
		model.addAttribute("mailList",mailService.receiveMail(email));
		System.out.println("receiver는 ? "+session.getAttribute("Email"));
		
		mailVO.setSender((String) session.getAttribute("Email"));
		mailVO.setReferrer((String) session.getAttribute("Email"));
		String S = "S";
		mailVO.setMailType(S);
		cri.setPerPageNum(2); //페이징 2개로 끊어서 보려고 임시로 적어둔것
		Paging paging = new Paging();
        paging.setCri(cri);
        System.out.println(mailVO);
        paging.setTotalCount(mailService.countReceiveMail((String) session.getAttribute("Email"),(String) session.getAttribute("Email")));
		model.addAttribute("mailList",mailService.deletedMail(cri,mailVO));
		model.addAttribute("paging", paging);
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
	public String sendingMailForm(Criteria cri, Model model, MailVO mailVO, HttpServletRequest request,  @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("Email");
		model.addAttribute("mailList",mailService.sendingMail(email));
		System.out.println("receiver는 ? "+session.getAttribute("Email"));
		
		mailVO.setSender((String) session.getAttribute("Email"));
		String S = "S";
		mailVO.setMailType(S);
		cri.setPerPageNum(2);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(mailService.countSendMail((String) session.getAttribute("Email")));
		model.addAttribute("mailList",mailService.deletedMail(cri,mailVO));
		model.addAttribute("paging", paging);
		return "mail/sendingMail";
	}
	//지운메일함 폼
	@GetMapping("mail/deleteMail")
	public String deleteMailForm(Criteria cri,Model model, MailVO mailVO, HttpServletRequest request) {
		System.out.println("dm = "+mailVO);
		HttpSession session = request.getSession();
		mailVO.setSender((String) session.getAttribute("Email"));
		mailVO.setReceiver((String) session.getAttribute("Email"));
		model.addAttribute("mailList",mailService.deletedMail(cri,mailVO));
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
