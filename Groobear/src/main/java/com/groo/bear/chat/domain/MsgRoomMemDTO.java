package com.groo.bear.chat.domain;

import lombok.Getter;

@Getter
public class MsgRoomMemDTO {
	    private final Integer roomNo;
	    private final String id;
	    private final Integer msgNo;  // null이 허용됨
	    private final Character msgCheck;  // null이 허용됨

	    public MsgRoomMemDTO(Integer roomNo, String id, Integer msgNo, Character msgCheck) {
	        this.roomNo = roomNo;
	        this.id = id;
	        this.msgNo = msgNo;
	        this.msgCheck = msgCheck;
	    }
}
