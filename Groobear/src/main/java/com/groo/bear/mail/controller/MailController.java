package com.groo.bear.mail.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
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

import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.mail.service.MailService;
import com.groo.bear.mail.service.MailVO;
import com.groo.bear.mail.service.impl.EmailReader;
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
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws ParseException, MessagingException {
		//여기서 EmailReader 내용 들어와야하고, db에 넣는부분 필요하고, db에 넣고나면 그거 읽어와서 화면에 뿌려줘야됨
		String id = (String) session.getAttribute("Id");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-31");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-31");
        String id2=id;
        id = id.substring(0,id.indexOf("@"));
        EmailReader receiver = new EmailReader();
        receiver.setSaveDirectory("/home/ec2-user/upload/mail");
        List<MailVO> list = receiver.receiveMailAttachedFile(id, id, startDate, endDate);
        //위에서 가져온 메일을 db에 저장하고 뿌려줌
		if(list!=null && list.size() > 0) {
			for(int i=0; i < list.size();i++) {
				System.out.println("list.get(i)첵 = "+list.get(i));
				int result = mailService.serverGetInsertMail(list.get(i));
				List<String> files = list.get(i).getFiles(); //첨부파일리스트
				if(files != null) {
					for(int j=0;j<files.size();j++) {
					//insert문 VO만들기
						FilesVO filesVO = new FilesVO();
						UUID uuid = UUID.randomUUID();
						filesVO.setUuid(uuid.toString());
						filesVO.setUploadPath("mail");
						filesVO.setFileName(files.get(i));
						filesVO.setReadMailNo(list.get(i).getMailNo());
						mailService.insertMailFile(filesVO);
					}
				}
		        System.out.println("몇건 처리됨? "+result);
			}
		}
		String R="R";
		mailVO.setReceiver(id);
		mailVO.setReferrer(id2);
		mailVO.setReferrer2(id2);
		mailVO.setReferrer3(id2); //(String) session.getAttribute("Id")
		mailVO.setMailType(R);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(mailService.countReceiveMail(mailVO));
		model.addAttribute("mailList",mailService.getMailList(cri,mailVO));
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
		
		return "redirect:mail/sendingMail";
	}
	//보낸메일함
	@GetMapping("mail/sendingMail")
	public String sendingMailForm(Criteria cri, Model model, MailVO mailVO, HttpSession session,  @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		String id = (String) session.getAttribute("Id");
		String S = "S";
		mailVO.setId(id);
		mailVO.setSender(id);
		mailVO.setMailType(S);
		System.out.println("sendingMail = "+mailVO);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(mailService.countSendMail(mailVO));
        model.addAttribute("mailList",mailService.sendMailSearch(cri, mailVO));
		model.addAttribute("paging", paging);
		return "mail/sendingMail";
	}
	//지운메일함 폼
	@GetMapping("mail/deleteMail")
	public String deleteMailForm(Criteria cri, Model model, MailVO mailVO, HttpSession session) {
		String id = (String) session.getAttribute("Id");
		id = id.substring(0,id.indexOf("@"));
		mailVO.setSender(id);
		mailVO.setReceiver(id);
		mailVO.setReferrer(id+"bear.com");
		mailVO.setReferrer2(id+"bear.com");
		mailVO.setReferrer3(id+"bear.com");
		mailVO.setId(id);
		String U = "U";
		mailVO.setMailType(U);
		Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(mailService.countDeleteMail(mailVO));
		model.addAttribute("mailList",mailService.getMaildelete(cri,mailVO));
		model.addAttribute("paging", paging);
		
		return "mail/deleteMail";
	}
	//받은메일에서 삭제시 update문으로 delete_date,mail_type설정
	@PostMapping("mail/delete")
	@ResponseBody
	public int deleteMail(@RequestBody List<Integer> delList ,HttpSession session) {
		//update하는곳
		int count=0;
		String U = "U";
		String id = (String) session.getAttribute("Id");
		id = id.substring(0,id.indexOf("@"));
		String id2 = id+"@bear.com";
		for (int i=0; i< delList.size(); i++) {
			System.out.println("delete i = "+delList.get(i));
			MailVO mailVO = new MailVO();
			mailVO = mailService.getMailInfo(delList.get(i));
			mailVO.setMailType(U);
			if(mailVO.getReceiver().equals(id)) {
				mailService.getMailDelete(mailVO);
			}else if(mailVO.getReferrer().equals(id2)) {
				mailVO.setReferrer(id2);
				mailService.getMailDelete(mailVO);
			}else if(mailVO.getReferrer().equals(id2)) {
				mailVO.setReferrer(id2);
				mailService.getMailDelete(mailVO);
			}
			
//			if(mailVO.getReferrer2().equals("") || mailVO.getReferrer2() == null) {
//				System.out.println("referrer 없음");
//			}else {
//				System.out.println("referrer 있음");
//			}
//			if(mailVO.getReferrer2().equals(id2)) { //null체크
//				mailService.getMailType4Del(mailVO);
//			}else if(mailVO.getReferrer3().equals(id2)) {
//				mailService.getMailType5Del(mailVO);
//			}
			count++;
		}
	    System.out.println("count - "+count);
		return count;
	}
	//메일 상세조회
	@GetMapping("mailInfo")
	public String mailInfo(@RequestParam int mailNo,@RequestParam String check, Model model, HttpSession session) {
		//해당 메일 정보 조회
		System.out.println("상세조회"+check);
		MailVO mailVO = new MailVO();
		String id = (String)session.getAttribute("Id");
		if(check.equals("S")) {
			mailVO.setId(id);
			mailVO.setMailNo(mailNo);
			model.addAttribute("mail",mailService.mailInfo(mailVO));
			model.addAttribute("refCheck","A");
		}else {
		//model.addAttribute("mail",mailService.getMailInfo(mailNo));
			System.out.println("서버 메일 상세조회"+mailService.getMailInfo(mailNo));
			mailVO = mailService.getMailInfo(mailNo);
			System.out.println("mailInfo페이지 ="+mailVO);
			model.addAttribute("refCheck","B");
			String user = mailVO.getReceiver()+"@bear.com"; 
			if(user.equals(id)) {
				mailVO.setMailNo(mailNo);
				String checks = "Y";
				mailVO.setMailNo(mailNo);
				mailVO.setReadCheck(checks);
				System.out.println("Receiver = session : "+mailVO);
				mailService.getMailInfoUpdate(mailVO);
				model.addAttribute("mail",mailService.getMailInfo(mailNo));
			}
		}
		return "mail/mailInfo";
	}
	//지운메일함에서 삭제시 완전삭제(db에서 delete)
	@PostMapping("mail/realdelete")
	@ResponseBody
	public int realDeleteMail(@RequestBody List<Integer> delList, HttpSession session) {
		//update하는곳
		int count=0;
		String D = "D";
		String id = (String) session.getAttribute("Id");
		String id2= id;
		id = id.substring(0,id.indexOf("@"));
		for (int i=0; i< delList.size(); i++) {
			System.out.println("서버에서 보내준 데이터 테이블참조");
			MailVO mailVO = new MailVO();
			mailVO = mailService.getMailInfo(delList.get(i));
			mailVO.setMailType(D);
			if(mailVO.getReceiver().equals(id)) {
				mailService.getMailDelete(mailVO);
			}else if(mailVO.getReferrer().equals(id2)) {
				mailVO.setReferrer(id2);
				mailService.getMailDelete(mailVO);
			}
			count++;
//			else if(mailVO.getReferrer2().equals(id)) {
//				mailService.getMailType4Del(mailVO);
//			}else if(mailVO.getReferrer3().equals(id)) {
//				mailService.getMailType5Del(mailVO);
//			}
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
	//받은메일에서 삭제시 update문으로 delete_date,mail_type설정
	@PostMapping("mail/sendDelete")
	@ResponseBody
	public int sendDeleteMail(@RequestBody List<Integer> delList,HttpSession session) {
		//update하는곳
		int count=0;
		String id = (String) session.getAttribute("Id");
		for (int i=0; i< delList.size(); i++) {
			System.out.println("delete i = "+delList.get(i));
			MailVO mailVO = new MailVO();
			mailVO.setMailNo(delList.get(i));
			mailVO.setId(id);
			int result = mailService.deleteMail(mailVO);
			count++;
		}
	    System.out.println("count - "+count);
		return count;
	}
}
