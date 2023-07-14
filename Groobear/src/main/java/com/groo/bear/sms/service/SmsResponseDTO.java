package com.groo.bear.sms.service;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SmsResponseDTO {
	private String requestId;
	private LocalDateTime requestTime;
	private String statusCode;
	private String statusName;
	private String smsConfirmNum;
	
	public SmsResponseDTO(String smsConfirmNum) {
		this.smsConfirmNum = smsConfirmNum;
	}
}