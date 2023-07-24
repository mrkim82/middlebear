package com.groo.bear.pro.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
public class TimeTest { 
	
	@Test
	public void test() {
		// 현재 시간 구하기
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("현재 시간: " + currentDateTime);
        
        // 현재 시간에서 하루 전 시간 구하기
        LocalDateTime oneDayBefore = currentDateTime.minus(Duration.ofDays(1));
        System.out.println("하루 전 시간" + oneDayBefore);
	}
	
	@Test
	public void test2() {
		// 현재 시간 구하기
		Date currentDate = new Date();
        System.out.println("현재 시간2: " + currentDate);
	}
	
	@Test
	public void test3() {
		// 현재 시간 구하기
        Date currentDate = new Date();
        System.out.println("현재 시간: " + currentDate);

        // 하루 전 시간 구하기
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1); // 하루를 빼줌
        Date oneDayBefore = calendar.getTime();

        System.out.println("하루 전 시간: " + oneDayBefore);
	}
	
}