package com.groo.bear.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.mail.mapper.MailMapper;
import com.groo.bear.mail.service.MailService;
import com.groo.bear.mail.service.MailVO;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	MailMapper mailMapper;
	
	@Override
	public int sendMail(MailVO mailVO) {
		return mailMapper.sendMail(mailVO);
	}

	//여기서 스케줄링 걸어줘야됨
	@Override
	public void SchedulMail(MailVO mailVO) {
		
	}
}
