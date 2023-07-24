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
	public int paymentDocForm(PaymentVO payVO) {
		return 0;
	}

	@Override
	public EmpVO payEmpInfo(String id) {
		return paymentMapper.payEmpInfo(id);
	}

	@Override
	public int paymentNo() {
		return paymentMapper.paymentNo();
	}

	@Override
	public int paymentLogBook(PaymentVO payVO) {
		return paymentMapper.paymentLogBook(payVO);
	}	
}
