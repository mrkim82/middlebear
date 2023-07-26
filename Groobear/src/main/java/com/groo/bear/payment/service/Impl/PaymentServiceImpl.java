package com.groo.bear.payment.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.files.mapper.FilesMapper;
import com.groo.bear.payment.mapper.PaymentMapper;
import com.groo.bear.payment.service.PaymentService;
import com.groo.bear.payment.service.PaymentVO;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentMapper paymentMapper;
	
	@Autowired
	FilesMapper filesMapper;
	
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

	@Override
	public int insertSignImg(EmpVO vo) {
		
		return filesMapper.insertSignImg(vo);
	}

	@Override
	public int deleteSignImg(int signNo) {
		return filesMapper.deleteSignImg(signNo);
	}

	@Override
	public FilesVO searchSignImg(int empNo) {
		return filesMapper.searchSignImg(empNo);
	}

	@Override
	public int logInsert(FilesVO vo) {
		return filesMapper.logInsert(vo);
	}

	@Override
	public int logDataInsert(PaymentVO payVO) {
		return paymentMapper.logDataInsert(payVO);
	}

	@Override
	public int offDataInsert(PaymentVO payVO) {
		return paymentMapper.offDataInsert(payVO);
	}

	@Override
	public int robinDataInsert(PaymentVO payVO) {
		return paymentMapper.robinDataInsert(payVO);
	}	
}
