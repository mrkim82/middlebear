package com.groo.bear.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
//TextWebSocketHandler -> 웹소켓에서 텍스트 메세지를 처리하는 메서드
public class ChatHandler extends TextWebSocketHandler{
	
	//데이터 선언부: 연결된 웹소켓 세션들을 저장할 리스트 
	//WebSocketSession은 클라이언트의 웹소켓 연결을 대표한다.
	private static List<WebSocketSession> list = new ArrayList<>();
		
		//메서드들
		//클라이언트로부터 메세지를 받았을 때 호출되는 메서드. 받은 메세지를 모든 클라이언트에게 전달.
	    @Override
	    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	        String payload = message.getPayload();
	        log.info("payload : " + payload);

	        for(WebSocketSession sess: list) {
	            sess.sendMessage(message);
	        }
	    }

	    /* Client가 접속 시 호출되는 메서드 */
	    //클라이언트가 웹 소켓에 연결되었을 때 호출되는 메서드이다. 연결된 세션을 리스트에 추가한다.
	    @Override
	    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

	        list.add(session);

	        log.info(session + " 클라이언트 접속");
	    }

	    /* Client가 접속 해제 시 호출되는 메서드드 */
	    //클라이언트가 웹소켓에 연결을 종료했을 때 호출되는 메서드이다. 리스트에서 해당 세션을 제거.
	    @Override
	    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

	        log.info(session + " 클라이언트 접속 해제");
	        list.remove(session);
	    }
	    
	    /* 결국 이 코드는 클라이언트가 웹 소켓에 연결하면, 그 세션을 리스트에 저장하고, 클라이언트가 메시지를 보내면
	     * 그 메세지를 모든 클라이언트에게 전송. 클라이언트가 연결을 종료하면, 그 세션을 리스트에서 제거 */
}
