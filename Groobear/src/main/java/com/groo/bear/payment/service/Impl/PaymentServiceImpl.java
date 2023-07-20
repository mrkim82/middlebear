package com.groo.bear.payment.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.payment.mapper.PaymentMapper;
import com.groo.bear.payment.service.PaymentService;
import com.groo.bear.payment.service.PaymentVO;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentMapper paymentMapper;
	
	@Override
	public List<EmpVO> payEmpList() {
		return paymentMapper.payEmpList();
	}

	@Override
	public int paymentDoc(PaymentVO payVO) {
		return 0;
	}
	
}
