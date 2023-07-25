package com.groo.bear.chat.domain;

import lombok.Getter;

@Getter
public class ChatUsersDTO {
	private final String userId;

    public ChatUsersDTO(String userId) {
        this.userId = userId;
    }
}
