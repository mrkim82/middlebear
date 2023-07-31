package com.groo.bear.alarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.groo.bear.alarm.service.AlarmVO;

@Validated
@Controller
public class AlarmController {
	
	@Autowired
	   SimpMessagingTemplate messagingTemplate;
	
	// 댓글 등록 알림
   @MessageMapping("/almSend/{id}")
   public void arm(AlarmVO vo, @DestinationVariable String id) throws Exception {
      messagingTemplate.convertAndSend("/alarmMan/almSend/" + id, vo);
   }
   
   @MessageMapping("/proAlm/{id}")
   public void proAlm(AlarmVO vo, @DestinationVariable String id) throws Exception {
      messagingTemplate.convertAndSend("/alarmMan/proAlm/" + id, vo);
   }
   
   @MessageMapping("/mailAlm/{id}")
   public void mailAlm(AlarmVO vo, @DestinationVariable String id) throws Exception {
      messagingTemplate.convertAndSend("/alarmMan/mailAlm/" + id, vo);
   }
   
   @MessageMapping("/payAlm/{id}")
   public void payAlm(AlarmVO vo, @DestinationVariable String id) throws Exception {
      messagingTemplate.convertAndSend("/alarmMan/payAlm/" + id, vo);
   }
   
   @MessageMapping("/chatAlm/{id}")
   public void chatAlm(AlarmVO vo, @DestinationVariable String id) throws Exception {
      messagingTemplate.convertAndSend("/alarmMan/chatAlm/" + id, vo);
   }
	
}
