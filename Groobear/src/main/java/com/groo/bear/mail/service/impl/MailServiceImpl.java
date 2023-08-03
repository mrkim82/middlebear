package com.groo.bear.mail.service.impl;

import java.util.Date;
import java.util.List;
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
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.groo.bear.board.mapper.BoardMapper;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.files.mapper.FilesMapper;
import com.groo.bear.mail.mapper.MailMapper;
import com.groo.bear.mail.service.MailService;
import com.groo.bear.mail.service.MailVO;
import com.groo.bear.paging.Criteria;

import lombok.Setter;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	JavaMailSender mailSender;
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
	@Autowired
	MailMapper mailMapper;
	@Autowired
	FilesMapper filesMapper;
	@Override
	public int sendMail(MailVO mailVO) {
		return mailMapper.sendMail(mailVO);
	}
	//스케줄링
	@Override
	//@Scheduled(fixedDelay = 5*1000)
	public List<MailVO> mailAllList() {
		System.out.println("스케줄러");
		
		List<MailVO> list = mailMapper.mailAllList(null); 
		for(int i=1; i<list.size(); i++) {
			mailSend(list.get(i));
			System.out.println(list.get(i));
			System.out.println("메일번호 : "+list.get(i).getMailNo());
			mailSetUpdate(list.get(i).getMailNo());
		}
		return list;
	}
	
	//자바메일 전송하는 구문 여기에 옮기기
	public void mailSend(MailVO mailVO) {
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
	}
	@Override
	public int mailSetUpdate(int mailNo) {
		//메일(서버로 전송) mail_set = 'N'이면
		mailMapper.mailSetUpdate(mailNo);
		return 0;
	}
	@Override
	public List<MailVO> receiveMail(String email) {
		//받은메일함 조회페이지
		return mailMapper.receiveMail(email);
	}
	@Override
	public List<MailVO> sendingMail(String email) {
		//보낸메일함 조회페이지
		return mailMapper.sendingMail(email);
	}
	@Override
	public List<MailVO> deletedMail(Criteria cri,MailVO mailVO) {
		//지운메일함 조회페이지
		return mailMapper.deletedMail(cri, mailVO);
	}
	@Override
	public int deleteMail(MailVO mailVO) {
		//메일지우기(update)
		return mailMapper.deleteMail(mailVO);
	}
	@Override
	public int realDeleteMail(MailVO mailVO) {
		//메일지우기(delete)
		return mailMapper.realDeleteMail(mailVO);
	}
	@Override
	public MailVO mailInfo(MailVO mailVO) {
		//메일 상세조회페이지
		return mailMapper.mailInfo(mailVO);
	}
	@Override
	public int countSendMail(MailVO mailVO) {
		// 보낸메일 총 갯수
		System.out.println("sendmail count = "+mailMapper.countSendMail(mailVO));
		return mailMapper.countSendMail(mailVO);
	}
	@Override
	public int countReceiveMail(MailVO mailVO) {
		// 받은메일 총 갯수
		System.out.println("receiver = "+mailMapper.countReceiveMail(mailVO));
		return mailMapper.countReceiveMail(mailVO);
	}
	@Override
	public int countDeleteMail(MailVO mailVO) {
		// 지운메일 총 갯수
		return mailMapper.countDeleteMail(mailVO);
	}
	@Override
	public int insertMailFile(FilesVO vo) {
		return filesMapper.insertMailFile(vo);
	}
	@Override
	public int deleteMailFile(int mailNo) {
		return filesMapper.deleteMailFile(mailNo);
	}
	@Override
	public FilesVO searchMailFile(int mailNo) {
		return filesMapper.searchMailFile(mailNo);
	}
	@Override
	public int mailNo() {
		return mailMapper.mailNo();
	}
	@Override
	public int mailCheck(int mailNo) {
		return mailMapper.mailCheck(mailNo);
	}
	@Override
	public int insertPersonnel(MailVO mailVO) {
		return mailMapper.insertPersonnel(mailVO);
	}
	@Override
	public int personnelNo() {
		return mailMapper.personnelNo();
	}
	@Override
	public String userIdGet(String email) {
		return mailMapper.userIdGet(email);
	}
	
	@Setter(onMethod_= @Autowired)
	private MailMapper mapper;
	
	@Setter(onMethod_= @Autowired)
	private FilesMapper attachMapper;

	@Override
	public List<FilesVO> getAttach(int mailNo) {
		//log.info("get Attach list by boardNo" + boardNo);
		return attachMapper.findByNo("mail_no",mailNo);
	}
	@Override
	public int serverGetInsertMail(MailVO mailVO) {
		return mailMapper.serverGetInsertMail(mailVO);
	}
	
}
