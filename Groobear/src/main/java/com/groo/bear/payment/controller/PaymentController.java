package com.groo.bear.payment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.paging.Criteria;
import com.groo.bear.paging.Paging;
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
		System.out.println("id = "+id);
		int payNo = paymentService.paymentNo();
		System.out.println(payNo);
		model.addAttribute("paymentNo",paymentService.paymentNo());
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/paymentDoc";
	}
	//운행일지 작성
	@PostMapping("paymentDocLog")
	@ResponseBody
	public String paymentDocLog(Model model, @RequestBody PaymentVO payVO) {
		System.out.println("DocLog = "+payVO);
		paymentService.paymentInsert(payVO);
		paymentService.logDataInsert(payVO);
		return "성공";
	}
	//연차계 작성
	@PostMapping("paymentDocOff")
	@ResponseBody
	public String paymentDocOff(Model model, @RequestBody PaymentVO payVO) {
		System.out.println("DocOff = "+payVO);
		paymentService.paymentInsert(payVO);
		paymentService.offDataInsert(payVO);
		return "성공";
	}
	//품의서 작성
	@PostMapping("paymentDocRobin")
	@ResponseBody
	public String paymentDocRobin(Model model, @RequestBody PaymentVO payVO) {
		System.out.println("DocOff = "+payVO);
		paymentService.paymentInsert(payVO);
		paymentService.robinDataInsert(payVO);
		return "성공";
	}
	//전자서명 페이지
	@GetMapping("pay/paySign")
	public String paySignForm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		EmpVO empVO = new EmpVO();
		empVO = paymentService.payEmpInfo(id);
		System.out.println("payEmp = "+empVO);
		model.addAttribute("userInfo",empVO);
		return "pay/paySign";
	}
	//개인전자서명 등록
	@PostMapping("mypaySign")
	@ResponseBody
	public String mypaySign(@RequestBody EmpVO empVO) {
		System.out.println("ajax에서 다시보낸 정보"+empVO);
		paymentService.insertSignImg(empVO);
		return "성공";
	}
	//전자결재 확인하는 페이지
	@GetMapping("pay/payPreferences")
	public String payPreferences(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("Id");
		EmpVO empVO = new EmpVO();
		empVO = paymentService.payEmpInfo(id);
		System.out.println("payEmp = "+empVO);
		model.addAttribute("userInfo",empVO);
		System.out.println("EmpNo = "+empVO.getEmpNo());
		FilesVO fileVO = new FilesVO();
		fileVO = paymentService.searchSignImg(empVO.getEmpNo());
		System.out.println("search = "+fileVO);
		model.addAttribute("userSignInfo",fileVO);
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		return "pay/payPreferences";
	}
	//개인 서명 제거
	@PostMapping("signdelete")
	@ResponseBody
	public int signdelete(@RequestBody int signNo) {
		System.out.println("signNo! = "+signNo);
		int result = paymentService.deleteSignImg((int) signNo);
		return result;
	}
	//운행일지 파일등록
	@PostMapping("logInsert")
	@ResponseBody
	public int logInsert(@RequestBody FilesVO vo) {
		int result = paymentService.logInsert(vo);
		return result;
	}
	
	//결재 완료(반려)된 문서 조회
	@GetMapping("pay/completePay")
	public String CompletePayForm(Criteria cri, Model model, HttpSession session,PaymentVO payVO, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		String id = (String) session.getAttribute("Id");
		System.out.println("id = "+id);
		payVO.setId(id);
		cri.setPerPageNum(10);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(paymentService.completePayCount(id));
        System.out.println("에러위치체크");
        model.addAttribute("userid",id);
        model.addAttribute("completePaymentList",paymentService.completePaymentList(cri, payVO));
        model.addAttribute("paging",paging);
		return "pay/completePay";
	}
	//참조된 문서 조회
	@GetMapping("pay/referrerPay")
	public String referrerPayForm(Criteria cri, Model model, HttpSession session,PaymentVO payVO, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		String id = (String) session.getAttribute("Id");
		System.out.println("id = "+id);
		payVO.setId(id);
		cri.setPerPageNum(10);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(paymentService.referrerPayCount(id));
        System.out.println("에러위치체크");
        model.addAttribute("userid",id);
        model.addAttribute("referrerList",paymentService.referrerPayList(cri, payVO));
        model.addAttribute("paging",paging);
		return "pay/referrerPay";
	}
	//메인페이지
	@GetMapping("pay/payMain")
	public String payMainForm(Criteria cri, Model model, HttpSession session,PaymentVO payVO, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		//결재중문서조회
		String id = (String) session.getAttribute("Id");
		System.out.println("id = "+id);
		payVO.setId(id);
		cri.setPerPageNum(5);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(paymentService.countPaymentList(id));
        System.out.println(paging);
        System.out.println("payment"+paymentService.paymentList(cri, payVO));
        model.addAttribute("userid",id);
		model.addAttribute("InProgressList",paymentService.paymentList(cri, payVO));
		model.addAttribute("paging", paging);
		
		//완료,반려된 문서 조회
		id = (String) session.getAttribute("Id");
		System.out.println("id = "+id);
		payVO.setId(id);
		cri.setPerPageNum(10);
		paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(paymentService.completePayCount(id));
        System.out.println("에러위치체크");
        model.addAttribute("userid",id);
        model.addAttribute("completePaymentList",paymentService.completePaymentList(cri, payVO));
        model.addAttribute("paging",paging);
		
		//참조된 문서조회
		id = (String) session.getAttribute("Id");
		System.out.println("id = "+id);
		payVO.setId(id);
		cri.setPerPageNum(5);
		paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(paymentService.referrerPayCount(id));
        System.out.println("에러위치체크");
        model.addAttribute("userid",id);
        model.addAttribute("referrerList",paymentService.referrerPayList(cri, payVO));
        model.addAttribute("paging",paging);
		return "pay/payMain";
	}
	//결재 진행중인 문서 조회
	@GetMapping("pay/InProgressPay")
	public String InProgressPayForm(Criteria cri, Model model, HttpSession session,PaymentVO payVO, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		String id = (String) session.getAttribute("Id");
		System.out.println("id = "+id);
		payVO.setId(id);
		cri.setPerPageNum(10);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(paymentService.countPaymentList(id));
        System.out.println(paging);
        System.out.println("payment"+paymentService.paymentList(cri, payVO));
        model.addAttribute("userid",id);
		model.addAttribute("InProgressList",paymentService.paymentList(cri, payVO));
		model.addAttribute("paging", paging);
		return "pay/InProgressPay";
	}
	//결재문서 상세조회
	@GetMapping("payInfo")
	public String payInfo(@RequestParam int payNo, @RequestParam String docType, Model model, HttpSession session) {
		System.out.println("payInfo payNo = "+payNo);
		System.out.println("payInfo docType = "+docType);
		String id = (String) session.getAttribute("Id");
		EmpVO empvo = new EmpVO();
		empvo = paymentService.payEmpInfo(id);
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		model.addAttribute("mySign",paymentService.searchSignImg(empvo.getEmpNo()));
		if(docType.equals("a")) {
			System.out.println("docType = 운행일지");
			PaymentVO payVO = paymentService.logList(payNo);
			System.out.println("payVO = "+payVO);
			empvo = paymentService.payEmpInfo(payVO.getId());
			model.addAttribute("drafterInfo",empvo);
			model.addAttribute("list",paymentService.logList(payNo));
			model.addAttribute("drafterSign",paymentService.searchSignImg(empvo.getEmpNo()));
			empvo = paymentService.payEmpInfo(payVO.getApprover2());
			model.addAttribute("approver2Info",empvo);
			model.addAttribute("approver2Sign",paymentService.searchSignImg(empvo.getEmpNo()));
			//결재자3이 있으면 보내줌
			String approver3 = payVO.getApprover3();
			if(approver3 == null || approver3 =="") {
				System.out.println("조건 임시변경 테스트");
			}else {
				System.out.println("제대로찍힘");
				if(empvo != null) {
					empvo = paymentService.payEmpInfo(payVO.getApprover3());
					model.addAttribute("approver3Info",empvo);
					model.addAttribute("approver3Sign",paymentService.searchSignImg(empvo.getEmpNo()));
					System.out.println("결재자2"+empvo);
					System.out.println(paymentService.searchSignImg(empvo.getEmpNo()));
				}
			}
		}
		if(docType.equals("b")) {
			System.out.println("docType = 연차계");
			System.out.println("payNo"+payNo);
			PaymentVO payVO = paymentService.offList(payNo);
			System.out.println("payVO = "+payVO);
			empvo = paymentService.payEmpInfo(payVO.getId());
			model.addAttribute("drafterInfo",empvo);
			model.addAttribute("list",paymentService.offList(payNo));
			model.addAttribute("drafterSign",paymentService.searchSignImg(empvo.getEmpNo()));
			empvo = paymentService.payEmpInfo(payVO.getApprover2());
			model.addAttribute("approver2Info",empvo);
			model.addAttribute("approver2Sign",paymentService.searchSignImg(empvo.getEmpNo()));
			//결재자3이 있으면 보내줌
			String approver3 = payVO.getApprover3();
			if(approver3 == null || approver3 =="") {
				System.out.println("조건 임시변경 테스트");
			}else {
				System.out.println("제대로찍힘");
				if(empvo != null) {
					empvo = paymentService.payEmpInfo(payVO.getApprover3());
					model.addAttribute("approver3Info",empvo);
					model.addAttribute("approver3Sign",paymentService.searchSignImg(empvo.getEmpNo()));
					System.out.println("결재자2"+empvo);
					System.out.println(paymentService.searchSignImg(empvo.getEmpNo()));
				}
			}
		}
		if(docType.equals("c")){
			System.out.println("docType = 품의서");
			PaymentVO payVO = paymentService.robinList(payNo);
			System.out.println("payVO = "+payVO);
			empvo = paymentService.payEmpInfo(payVO.getId());
			model.addAttribute("drafterInfo",empvo);
			model.addAttribute("list",paymentService.robinList(payNo));
			model.addAttribute("drafterSign",paymentService.searchSignImg(empvo.getEmpNo()));
			empvo = paymentService.payEmpInfo(payVO.getApprover2());
			model.addAttribute("approver2Info",empvo);
			model.addAttribute("approver2Sign",paymentService.searchSignImg(empvo.getEmpNo()));
			//결재자3이 있으면 보내줌
			String approver3 = payVO.getApprover3();
			if(approver3 == null || approver3 =="") {
				System.out.println("조건 임시변경 테스트");
			}else {
				System.out.println("제대로찍힘");
				if(empvo != null) {
					empvo = paymentService.payEmpInfo(payVO.getApprover3());
					model.addAttribute("approver3Info",empvo);
					model.addAttribute("approver3Sign",paymentService.searchSignImg(empvo.getEmpNo()));
					System.out.println("결재자2"+empvo);
					System.out.println(paymentService.searchSignImg(empvo.getEmpNo()));
				}
			}
		}
		List<FilesVO> list = paymentService.searchPayImg(payNo);
		System.out.println(list);
		model.addAttribute("payImgInfo",list);
		return "pay/payInfo";
	}
	//문서 상세조회 페이지에서 수정
	@PostMapping("paymentUpdate")
	@ResponseBody
	public String paymentUpdate(@RequestBody PaymentVO payVO, Model model) {
		System.out.println("update docType = "+payVO.getDocType());
		if(payVO.getDocType().equals("a")) {
			System.out.println("payVo = "+payVO);
			paymentService.logUpdate(payVO);
			//첨부파일도 있으면 업데이트 서비스 넣어줘야됨
			System.out.println("운행일지 업데이트 완료");
		}else if(payVO.getDocType().equals("b")) {
			paymentService.offUpdate(payVO);
			//첨부파일도 있으면 업데이트 서비스 넣어줘야됨
			System.out.println("연차계 업데이트 완료");
		}else {
			paymentService.robinUpdate(payVO);
			//첨부파일도 있으면 업데이트 서비스 넣어줘야됨
			System.out.println("품의서 업데이트 완료");
		}
		
		return "pay/InProgressPay";
	}
	//문서 결재(반려)
	@PostMapping("paymentReject")
	@ResponseBody
	public String paymentReject(@RequestBody PaymentVO payVO, Model model, HttpSession session) {
		System.out.println("payVO = "+payVO);
		String id = (String) session.getAttribute("Id");
		if(payVO.getApprover2().equals(id)){
			paymentService.paymentReject2(payVO);
			System.out.println(payVO.getPayNo()+"문서 결재자2 결재상태변경완료");
		}else if(payVO.getApprover3().equals(id)) {
			paymentService.paymentReject3(payVO);
			System.out.println(payVO.getPayNo()+"문서 결재자3 결재상태변경완료");
		}
		//해당위치에서 docType='a'면 com추가
		if(payVO.getDocType().equals("a")){
			paymentService.logCommentUpdate(payVO);
		}else if(payVO.getDocType().equals("b")){
			paymentService.offCommentUpdate(payVO);
		}else {
			paymentService.robinCommentUpdate(payVO);
		}
		
		return "pay/InProgressPay";
	}
	//결재문서 파일삭제
	@PostMapping("payfiledel")
	@ResponseBody
	public int payfileDel(@RequestBody PaymentVO payVO, Model model, HttpSession session) {
		int result = 0;
		System.out.println("payVO.getpayNo = "+payVO.getPayNo());
		result = paymentService.deletePayImg(payVO.getPayNo());
		
		System.out.println("result = "+result);
		return result;
	}
	//첨부파일 다운로드
	@GetMapping(value ="/getAtt", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<FilesVO>> getAtt(@RequestBody @RequestParam int payNo) {
		//log.info("getAttachList" + payNo);
		return new ResponseEntity<>(paymentService.getAtt(payNo), HttpStatus.OK);
	}
	//결재문서 상세조회
	@GetMapping("payInfoRead")
	public String payInfoRead(@RequestParam int payNo, @RequestParam String docType, Model model, HttpSession session) {
		System.out.println("payInfo payNo = "+payNo);
		System.out.println("payInfo docType = "+docType);
		String id = (String) session.getAttribute("Id");
		EmpVO empvo = new EmpVO();
		empvo = paymentService.payEmpInfo(id);
		System.out.println("empvo123 = "+empvo);
		model.addAttribute("userInfo",paymentService.payEmpInfo(id));
		model.addAttribute("mySign",paymentService.searchSignImg(empvo.getEmpNo()));
		if(docType.equals("a")) {
			System.out.println("docType = 운행일지");
			PaymentVO payVO = paymentService.logList(payNo);
			System.out.println("payVO = "+payVO);
			empvo = paymentService.payEmpInfo(payVO.getId());
			System.out.println("a empvo1 = "+empvo);
			model.addAttribute("drafterInfo",empvo);
			model.addAttribute("list",paymentService.logList(payNo));
			model.addAttribute("drafterSign",paymentService.searchSignImg(empvo.getEmpNo()));
			empvo = paymentService.payEmpInfo(payVO.getApprover2());
			System.out.println("a empvo2 = "+empvo);
			model.addAttribute("approver2Info",empvo);
			model.addAttribute("approver2Sign",paymentService.searchSignImg(empvo.getEmpNo()));
			//결재자3이 있으면 보내줌
			String approver3 = payVO.getApprover3();
			System.out.println("a payvo1 = "+payVO);
			System.out.println("a approver1"+approver3);
			if(approver3 == null || approver3 =="") {
				System.out.println("조건 임시변경 테스트");
			}else {
				System.out.println("제대로찍힘");
				if(empvo != null) {
					empvo = paymentService.payEmpInfo(payVO.getApprover3());
					model.addAttribute("approver3Info",empvo);
					model.addAttribute("approver3Sign",paymentService.searchSignImg(empvo.getEmpNo()));
					System.out.println("결재자2"+empvo);
					System.out.println(paymentService.searchSignImg(empvo.getEmpNo()));
				}
			}
		}
		else if(docType.equals("b")) {
			System.out.println("docType = 연차계");
			System.out.println("payNo"+payNo);
			PaymentVO payVO = paymentService.offList(payNo);
			System.out.println("payVO = "+payVO);
			empvo = paymentService.payEmpInfo(payVO.getId());
			model.addAttribute("drafterInfo",empvo);
			model.addAttribute("list",paymentService.offList(payNo));
			model.addAttribute("drafterSign",paymentService.searchSignImg(empvo.getEmpNo()));
			empvo = paymentService.payEmpInfo(payVO.getApprover2());
			model.addAttribute("approver2Info",empvo);
			model.addAttribute("approver2Sign",paymentService.searchSignImg(empvo.getEmpNo()));
			//결재자3이 있으면 보내줌
			String approver3 = payVO.getApprover3();
			if(approver3 == null || approver3 =="") {
				System.out.println("조건 임시변경 테스트");
			}else {
				System.out.println("제대로찍힘");
				if(empvo != null) {
					empvo = paymentService.payEmpInfo(payVO.getApprover3());
					model.addAttribute("approver3Info",empvo);
					model.addAttribute("approver3Sign",paymentService.searchSignImg(empvo.getEmpNo()));
					System.out.println("결재자2"+empvo);
					System.out.println(paymentService.searchSignImg(empvo.getEmpNo()));
				}
			}
		}
		else if(docType.equals("c")){
			System.out.println("docType = 품의서");
			PaymentVO payVO = paymentService.robinList(payNo);
			System.out.println("payVO = "+payVO);
			empvo = paymentService.payEmpInfo(payVO.getId());
			model.addAttribute("drafterInfo",empvo);
			model.addAttribute("list",paymentService.robinList(payNo));
			model.addAttribute("drafterSign",paymentService.searchSignImg(empvo.getEmpNo()));
			empvo = paymentService.payEmpInfo(payVO.getApprover2());
			model.addAttribute("approver2Info",empvo);
			model.addAttribute("approver2Sign",paymentService.searchSignImg(empvo.getEmpNo()));
			//결재자3이 있으면 보내줌
			String approver3 = payVO.getApprover3();
			if(approver3 == null || approver3 =="") {
				System.out.println("조건 임시변경 테스트");
			}else {
				System.out.println("제대로찍힘");
				if(empvo != null) {
					empvo = paymentService.payEmpInfo(payVO.getApprover3());
					model.addAttribute("approver3Info",empvo);
					model.addAttribute("approver3Sign",paymentService.searchSignImg(empvo.getEmpNo()));
					System.out.println("결재자2"+empvo);
					System.out.println(paymentService.searchSignImg(empvo.getEmpNo()));
				}
			}
		}
		List<FilesVO> list = paymentService.searchPayImg(payNo);
		System.out.println(list);
		model.addAttribute("payImgInfo",list);
		return "pay/payInfoRead";
	}
	//공용서명 등록
	
}
