package com.groo.bear.payment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.payment.service.PaymentService;
import com.groo.bear.payment.service.PaymentVO;

//김영환 1.전자결재
@Controller
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	//결재자 사원 받아와서 모달창에 뿌려주는 controller
	@GetMapping("pay/paymentEmp")
	public String payEmpList(Model model,HttpServletRequest request) {
		model.addAttribute("payEmpList",paymentService.payEmpList());
		HttpSession session = request.getSession();
		System.out.println("session = "+session);
		String id = (String) session.getAttribute("Id");
		System.out.println("id = "+id);
		System.out.println("기안자 : "+paymentService.payEmpInfo(id));
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		
		return "pay/paymentEmp";
	}
	//참조자 사원 받아와서 모달창에 뿌려주는 controller
	@GetMapping("pay/referrerEmp")
	public String referrerEmp(Model model, HttpServletRequest request) {
		model.addAttribute("payEmpList",paymentService.payEmpList());
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/referrerEmp";
	}
	//결재문서 페이지
	@GetMapping("pay/paymentDoc")
	public String paymentDocForm(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		int payNo = paymentService.paymentNo();
		System.out.println(payNo);
		model.addAttribute("paymentNo",paymentService.paymentNo());
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/paymentDoc";
	}
	//결재문서 작성
	@PostMapping("paymentDoc")
	@ResponseBody
	public String paymentDoc(Model model, @RequestBody PaymentVO payVO) {
		System.out.println(payVO);
		return "redirect:/main";
	}
	//전자서명 페이지
	@GetMapping("pay/paySign")
	public String paySignForm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/paySign";
	}
	//전자결재 확인하는 페이지
	@GetMapping("pay/payPreferences")
	public String payPreferences(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/payPreferences";
	}
}
