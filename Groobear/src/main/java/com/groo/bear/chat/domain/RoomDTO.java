package com.groo.bear.chat.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
//DTO 클래스들은 웹 레이어와 서비스 레이어 사이,
//혹은 서비스 레이어와 데이터베이스 레이어 사이에서 데이터를 교환하는데 사용
@Data
public class RoomDTO {
    private Integer roomNo;
    private String id;
    private String roomName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate roomTime;
    
    private int empNo;
    
    private String name;
    private String rank;
    private String deptName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memTime;
    private List<String> employeeIds;
    
    private int participantCount;
    private String lastMessage;
    private String content;
    
}
