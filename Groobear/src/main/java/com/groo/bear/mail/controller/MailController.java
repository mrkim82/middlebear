package com.groo.bear.mail.controller;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
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
	JavaMailSender mailSender;
	@Autowired
	MailService mailService;
	@Value("${spring.mail.host}") // 프로퍼티 혹은 빈에 있는 값들을 들고올 때 사용 (Spring value로 import)
	private String host;
	@Value("${spring.mail.port}") 
	private String port;
	@Value("${spring.mail.username}") 
	private String email;
	@Value("${spring.mail.password}") 
	private String password;
	@Value("${spring.mail.properties.mail.smtp.auth}") 
	private String auth;
	@Value("${spring.mail.properties.mail.transport.protocol}") 
	private String protocol;
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
        System.out.println("메일 전송 완료");
        Properties props = new Properties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);

        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        String sendTo = mailVO.getSender();
    	String mailTitle = mailVO.getTitle();
    	String mailContent = mailVO.getContent();
    	String receiver = mailVO.getReceiver();
    	//메일 전송부분
    	try {
            // 메일 세션 생성
            Session session2 = Session.getInstance(props);
            System.out.println("session2생성 : "+session2);
            // 메일 송/수신 옵션 설정
            Message message = new MimeMessage(session2);
            message.setFrom(new InternetAddress(sendTo, "김영환"));
            message.setRecipients(RecipientType.TO, InternetAddress.parse(receiver, false));
            message.setSubject(mailTitle);
            message.setSentDate(new Date());
            System.out.println("message 생성 : "+message);
            // 메일 콘텐츠 설정
            Multipart mParts = new MimeMultipart();
            MimeBodyPart mTextPart = new MimeBodyPart();
            MimeBodyPart mFilePart = null;

            // 메일 콘텐츠 - 내용
            mTextPart.setText(mailContent);
            mParts.addBodyPart(mTextPart);

            // 메일 콘텐츠 설정
            message.setContent(mParts);

            // MIME 타입 설정 html속성 메일발송시 적용시킬때 사용하는 구문들
            MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(MailcapCmdMap);
            System.out.println("발송전");
            // 메일 발송
            Transport.send(message,"kiss770096@gmail.com","bkzkyijdrfngvwnn");
            System.out.println("발송완료");
         } catch (Exception e) {
            e.printStackTrace();
         }
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
