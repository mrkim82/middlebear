package com.groo.bear.chat.domain;

import lombok.Getter;

@Getter
public class ChatUsersDTO {
	public String id;

	public ChatUsersDTO() {}
	
    public ChatUsersDTO(String id) {
        this.id = id;
    }
    
    
}
