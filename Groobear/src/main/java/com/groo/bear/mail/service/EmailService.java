package com.groo.bear.mail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
	private JavaMailSender emailSender;
	 
    public void sendSimpleMessage(MailVO mailVO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kiss770096@gmail.com");
        message.setTo(mailVO.getReceiver());
        message.setSubject(mailVO.getTitle());
        message.setText(mailVO.getContent());
        emailSender.send(message);
    }
}
