package com.groo.bear.chat.domain;

import java.util.List;

import lombok.Data;

@Data
public class CreateChatroomRequest {
	private RoomDTO room;
    private List<String> employeeIds;
}
