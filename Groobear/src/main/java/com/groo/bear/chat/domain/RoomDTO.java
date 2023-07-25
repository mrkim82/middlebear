package com.groo.bear.chat.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
//DTO 클래스들은 웹 레이어와 서비스 레이어 사이,
//혹은 서비스 레이어와 데이터베이스 레이어 사이에서 데이터를 교환하는데 사용
@Data
public class RoomDTO {
    private final int roomNo;
    private final String userId;
    private final String roomName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Date roomTime;

    public RoomDTO(int roomNo, String userId, String roomName, Date roomTime) {
        this.roomNo = roomNo;
        this.userId = userId;
        this.roomName = roomName;
        this.roomTime = roomTime;
    }
    
}
