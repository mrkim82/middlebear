package com.groo.bear.chat.domain;

import lombok.Getter;

@Getter
public class MsgRoomMemDTO {
	    private final int roomNo;
	    private final String userId;
	    private final Integer msgNo;  // null이 허용됨
	    private final Character msgCheck;  // null이 허용됨

	    public MsgRoomMemDTO(int roomNo, String userId, Integer msgNo, Character msgCheck) {
	        this.roomNo = roomNo;
	        this.userId = userId;
	        this.msgNo = msgNo;
	        this.msgCheck = msgCheck;
	    }
}
