package com.groo.bear.mail.controller;

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

import com.groo.bear.board.controller.BoardRestController;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.mail.service.MailService;
import com.groo.bear.mail.service.MailVO;
import com.groo.bear.paging.Criteria;
import com.groo.bear.paging.Paging;

import lombok.extern.log4j.Log4j2;

//김영환 1.메일
@Controller
@Log4j2
public class MailController {
	
	@Autowired
	MailService mailService;
	
	//받은메일함
	@GetMapping("mail/receiveMail")
	public String receiveMailForm(Criteria cri ,Model model, MailVO mailVO, HttpSession session ,  @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		mailVO.setReceiver((String) session.getAttribute("Id"));
		mailVO.setReferrer((String) session.getAttribute("Id"));
		mailVO.setId((String) session.getAttribute("Id"));
		String S = "S";
		mailVO.setMailType(S);
		System.out.println("11111111111"+mailVO);
		//cri.setPerPageNum(2); //페이징 2개로 끊어서 보려고 임시로 적어둔것
		System.out.println("받은메일함 mailVO"+mailVO);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(mailService.countReceiveMail((String) session.getAttribute("Id"),(String) session.getAttribute("Id")));
		model.addAttribute("mailList",mailService.deletedMail(cri,mailVO));
		model.addAttribute("paging", paging);
		return "mail/receiveMail";
	}
	//메일작성폼
	@GetMapping("mail/sendMail")
	public String sendMailForm(Model model,HttpSession session) {
		String id = (String) session.getAttribute("Id");
		model.addAttribute("id",id);
		model.addAttribute("mailNo",mailService.mailNo());
		
		return "mail/sendMail";
	}
	//메일 작성(발송)
	@PostMapping("sendMail")
	@ResponseBody
	public String sendMail(Model model,@RequestBody MailVO mailVO,HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("1231313"+mailVO);
		mailService.sendMail(mailVO);
		System.out.println("메일발송용"+mailVO);
		System.out.println("메일 전송 완료");
        int no = mailService.personnelNo();
        mailVO.setMailNo(no);
        String email = mailVO.getReceiver();
        String id = mailService.userIdGet(email);
        mailVO.setId(id);
        System.out.println(mailVO);
        mailService.insertPersonnel(mailVO);
        if(mailVO.getReferrer()!=null) {
        	String allreferrer = (String) mailVO.getReferrer();
        	String[] referrer = allreferrer.split(",");
        	for(int i=0;i<referrer.length;i++) {
        		email = referrer[i];
        		id = mailService.userIdGet(email);
        		System.out.println("42423"+id);
        		mailVO.setId(id);
        		mailService.insertPersonnel(mailVO);
        	}
        }
		return "redirect:mail/sendingMail";
	}
	//보낸메일함
	@GetMapping("mail/sendingMail")
	public String sendingMailForm(Criteria cri, Model model, MailVO mailVO, HttpSession session,  @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		String email = (String) session.getAttribute("Id");
		System.out.println("보낸메일함 id = "+email);
		mailVO.setSender(email);
		mailVO.setId(email);
		String S = "S";
		mailVO.setMailType(S);
		mailVO.setMailSet("Y");
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(mailService.countSendMail(email));
		model.addAttribute("mailList",mailService.deletedMail(cri,mailVO));
		model.addAttribute("paging", paging);
		return "mail/sendingMail";
	}
	//지운메일함 폼
	@GetMapping("mail/deleteMail")
	public String deleteMailForm(Criteria cri, Model model, MailVO mailVO, HttpSession session) {
		String id = (String) session.getAttribute("Id");
		mailVO.setSender((String) session.getAttribute(id));
		mailVO.setReceiver((String) session.getAttribute(id));
		mailVO.setReferrer((String) session.getAttribute(id));
		mailVO.setId(id);
		String D = "D";
		mailVO.setMailType(D);
		System.out.println("메일함 전체조회에서 폼"+mailVO);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(mailService.countDeleteMail(mailVO));
		model.addAttribute("mailList",mailService.deletedMail(cri,mailVO));
		System.out.println(mailService.deletedMail(cri,mailVO));
		model.addAttribute("paging", paging);
		return "mail/deleteMail";
	}
	//보낸메일,받은메일에서 삭제시 update문으로 delete_date,mail_type설정
	@PostMapping("mail/delete")
	@ResponseBody
	public int deleteMail(@RequestBody List<Integer> delList, HttpSession session) {
		//update하는곳
		int count=0;
		System.out.println("보낸메일함 삭제기능적용됨?"+delList);
	    for (int i = 0 ; i < delList.size() ; i++) { 
	    	System.out.println("여기서도 적용됨?");
	    	MailVO mailVO = new MailVO();
	    	mailVO.setId((String) session.getAttribute("Id"));
	    	mailVO.setMailNo(delList.get(i));
	    	System.out.println("delete = "+mailVO);
	        mailService.deleteMail(mailVO);
	        count++;
	    }
	    System.out.println("count - "+count);
		return count;
	}
	//메일 상세조회
	@GetMapping("mailInfo")
	public String mailInfo(@RequestParam int mailNo, Model model, HttpSession session) {
		//해당 메일 정보 조회
		String id = (String)session.getAttribute("Id");
		System.out.println("mailNo - "+mailNo);
		model.addAttribute("mailFile",mailService.searchMailFile(mailNo));
		System.out.println("첨부파일체크"+mailService.searchMailFile(mailNo));
		MailVO mailVO = new MailVO();
		mailVO.setId(id);
		mailVO.setMailNo(mailNo);
		model.addAttribute("mail",mailService.mailInfo(mailVO));
		System.out.println("mailVO = "+mailService.mailInfo(mailVO));
		mailVO = mailService.mailInfo(mailVO);
		if(mailVO.getReceiver().equals(id)) {
			mailService.mailCheck(mailNo);
		}
		return "mail/mailInfo";
	}
	//지운메일함에서 삭제시 완전삭제(db에서 delete)
	@PostMapping("mail/realdelete")
	@ResponseBody
	public int realDeleteMail(@RequestBody List<Integer> delList, HttpSession session) {
		//update하는곳
		int count=0;
	    for (int i = 0 ; i < delList.size() ; i++) { 
	    	MailVO mailVO = new MailVO();
	    	mailVO.setId((String) session.getAttribute("Id"));
	    	mailVO.setMailNo(delList.get(i));
	        mailService.realDeleteMail(mailVO);
	        mailService.deleteMailFile(delList.get(i));
	        count++;
	    }
		return count;
	}
	//메일 작성시 파일 등록
	@PostMapping("InsertMailFile")
	@ResponseBody
	public int InsertMailFile (@RequestBody FilesVO vo){
		mailService.insertMailFile(vo);
		return 1;
	}
	//메일에서 파일 삭제
	@PostMapping("DeleteMailFile")
	@ResponseBody
	public int payfileDel(@RequestBody MailVO mailVO, Model model, HttpSession session) {
		int result = 0;
		System.out.println("payVO.getpayNo = "+mailVO.getMailNo());
		result = mailService.deleteMailFile(result);
		
		System.out.println("result = "+result);
		return result;
	}
	
	// 첨부파일을 Ajax로 처리 했다면 서버에서 JSON 데이터를 만들어서 화면에 전송하는 작업 하기 위함.
	@GetMapping(value ="/getAttach", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<FilesVO>> getAttach(@RequestBody @RequestParam int mailNo) {
		log.info("getAttachList" + mailNo);
		return new ResponseEntity<>(mailService.getAttach(mailNo), HttpStatus.OK);
	}
}
