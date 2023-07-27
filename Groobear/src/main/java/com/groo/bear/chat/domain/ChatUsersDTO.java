package com.groo.bear.chat.domain;

import lombok.Getter;

@Getter
public class ChatUsersDTO {
	public String userId;

	public ChatUsersDTO() {}
	
    public ChatUsersDTO(String userId) {
        this.userId = userId;
    }
    
    
}
