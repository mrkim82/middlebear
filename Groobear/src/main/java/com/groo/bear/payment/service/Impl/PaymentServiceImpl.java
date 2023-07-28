package com.groo.bear.payment.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.files.mapper.FilesMapper;
import com.groo.bear.paging.Criteria;
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
	public int paymentInsert(PaymentVO payVO) {
		return paymentMapper.paymentInsert(payVO);
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

	@Override
	public List<PaymentVO> paymentList(Criteria cri, PaymentVO payVO) {
		return paymentMapper.paymentList(cri, payVO);
	}

	@Override
	public int countPaymentList(String id) {
		return paymentMapper.countPaymentList(id);
	}

	@Override
	public PaymentVO logList(int payNo) {
		return paymentMapper.logList(payNo);
	}

	@Override
	public PaymentVO offList(int payNo) {
		return paymentMapper.offList(payNo);
	}

	@Override
	public PaymentVO robinList(int payNo) {
		return paymentMapper.robinList(payNo);
	}

	@Override
	public int logUpdate(PaymentVO payVO) {
		return paymentMapper.logUpdate(payVO);
	}

	@Override
	public int offUpdate(PaymentVO payVO) {
		return paymentMapper.offUpdate(payVO);
	}

	@Override
	public int robinUpdate(PaymentVO payVO) {
		return paymentMapper.robinUpdate(payVO);
	}

	@Override
	public int paymentReject2(PaymentVO payVO) {
		return paymentMapper.paymentReject2(payVO);
	}

	@Override
	public int paymentReject3(PaymentVO payVO) {
		return paymentMapper.paymentReject3(payVO);
	}

	@Override
	public List<PaymentVO> completePaymentList(Criteria cri, PaymentVO payVO) {
		return paymentMapper.completePaymentList(cri, payVO);
	}

	@Override
	public int completePayCount(String id) {
		return paymentMapper.completePayCount(id);
	}

	@Override
	public List<PaymentVO> referrerPayList(Criteria cri, PaymentVO payVO) {
		return paymentMapper.referrerPayList(cri, payVO);
	}

	@Override
	public int referrerPayCount(String id) {
		return paymentMapper.referrerPayCount(id);
	}

	@Override
	public List<FilesVO> searchPayImg(int payNo) {
		return filesMapper.searchPayImg(payNo);
	}

	@Override
	public int deletePayImg(int payNo) {
		return filesMapper.deletePayImg(payNo);
	}	
}
