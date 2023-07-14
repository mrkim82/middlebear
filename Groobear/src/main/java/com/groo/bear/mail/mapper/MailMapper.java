package com.groo.bear.mail.mapper;

import com.groo.bear.mail.service.MailVO;

public interface MailMapper {
	public int sendMail(MailVO mailVO);
}
