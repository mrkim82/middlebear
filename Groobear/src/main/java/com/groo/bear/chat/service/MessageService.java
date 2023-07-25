package com.groo.bear.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groo.bear.chat.domain.Message;
import com.groo.bear.chat.respository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
	
    MessageRepository messageRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    
    //새로운 메세지를 생성 Message 객체를 인자로 받아서 저장하고 반환.
    @Transactional
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }
    
    //모든 메세지 조회
    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
