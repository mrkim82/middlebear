package com.groo.bear.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {
//채팅 메세지에 대한 정보를 저장
    private String roomId; //메세지가 속한 채팅방의 ID
    private String writer; //메세지를 작성한 사용자
    private String message; //메세지의 내용
}
