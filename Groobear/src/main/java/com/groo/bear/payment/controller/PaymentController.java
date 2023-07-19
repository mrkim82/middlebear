package com.groo.bear.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.groo.bear.payment.service.PaymentService;

@Controller
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	//결재자 사원 받아와서 모달창에 뿌려주는 controller
	@GetMapping("pay/paymentEmp")
	public String payEmpList(Model model) {
		model.addAttribute("payEmpList",paymentService.payEmpList());
		return "pay/paymentEmp";
	}
	//결재문서 페이지
	@GetMapping("pay/paymentDoc")
	public String paymentDocForm() {
		return "pay/paymentDoc";
	}
	//결재문서 작성
	@PostMapping("paymentDoc")
	public String paymentDoc() {
		return "redirect:/main";
	}
}
