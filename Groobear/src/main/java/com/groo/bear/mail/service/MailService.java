package com.groo.bear.mail.service;

import java.util.List;

import com.groo.bear.paging.Criteria;

public interface MailService {
	//메일 전송(발송)
	public int sendMail(MailVO mailVO);
	
	//메일 테이블 전체조회
	public List<MailVO> mailAllList();
	//메일 전체조회 발송 후 update
	public int mailSetUpdate(int mailNo);
	//받은메일함 조회
	public List<MailVO> receiveMail(String email);
	//보낸메일함 조회
	public List<MailVO> sendingMail(String email);
	//지운메일함 조회
	public List<MailVO> deletedMail(Criteria cri,MailVO mailVO);
	//메일삭제(update)
	public int deleteMail(int mailNo);
	//메일 완전삭제(delete)
	public int realDeleteMail(int mailNo);
	//메일 상세조회
	public MailVO mailInfo(int mailNo);
	// 보낸메일 총 갯수
	public int countSendMail(String sender);
	// 받은메일 총 갯수
	public int countReceiveMail(String receiver, String referrer);
	// 지운메일 총 갯수
	public int countDeleteMail(MailVO mailVO);
}
