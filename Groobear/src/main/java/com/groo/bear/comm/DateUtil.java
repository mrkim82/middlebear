package com.groo.bear.comm;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	//하루 전계산
	public static Date beforeOneDay() {
        Date currentDate = new Date();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date oneDayBefore = calendar.getTime();
        
		return oneDayBefore;
	}
	
	//하루 후계산
	public static Date afterOneDay() {
		Date currentDate = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date oneDayAfter = calendar.getTime();
		
		return oneDayAfter;
	}
}
