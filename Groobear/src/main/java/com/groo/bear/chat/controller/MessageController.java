package com.groo.bear.chat.controller;

import com.groo.bear.chat.domain.Message;
import com.groo.bear.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 모든 메서드의 반환값이 HTTP Response Body로 매핑된다.
@RequestMapping("/messages") // 컨트롤러가 처리할 수 있는 기본 URL을 지정한다.
//여기서 "/messages" URL로 오는 요청을 처리.
public class MessageController {
//"/messages" URL로 오는 HTTP 요청을 처리하여 새 메시지를 생성하거나
//, 모든 메시지를 검색하는 기능을 제공
	
    MessageService messageService;
    
    
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    
    
    @PostMapping // "/messages" URL로 들어오는 HTTP POST 요청을 처리한다.
    //즉, 새 메세지를 생성하는 역할.
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return new ResponseEntity<>(messageService.createMessage(message), HttpStatus.CREATED);
    }
    //createMessage(@RequestBody Message message) -> 클라이언트로부터 받은 메세지를 'MessageService'를 통해
    //데이터베이스에 저장하고, 저장된 메세지를 HTTP 응답 본문으로 반환한다.

    @GetMapping // "/messages" URL로 들어오는 HTTP GET 요청을 처리한다. 즉, 모든 메세지를 검색하는 역할을 한다.
    public ResponseEntity<List<Message>> getAllMessages() {
        return new ResponseEntity<>(messageService.getAllMessages(), HttpStatus.OK);
    }
    //getAllMessages() : 이 메서드는 'MessageService'를 통해 데이터베이스에서 모든 메세지를 검색하고, 검색된 메시지 리스트를
    //HTTP 응답 본문으로 반환한다.
    
}

