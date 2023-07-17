package com.groo.bear.mail.service;

public interface MailService {
	//메일 전송(발송)
	public int sendMail(MailVO mailVO);
	
	//메일 스케줄링
	public void SchedulMail(MailVO mailVO);
}
