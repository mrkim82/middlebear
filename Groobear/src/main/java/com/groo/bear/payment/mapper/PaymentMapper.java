package com.groo.bear.payment.mapper;

import java.util.List;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.payment.service.PaymentVO;

public interface PaymentMapper {
	//결재자선택에서 필요한 결재자 사원 리스트정보
	public List<EmpVO> payEmpList();
	//결재문서 페이지
	public int paymentDocForm(PaymentVO payVO);
	//기안자 id,emp_no,emp_name,dept_name 받아오는 거
	public EmpVO payEmpInfo(String id);
	//결재문서 작성시 db에서 결재문서 번호 가져와야됨 그래야 최초의 화면에서 문서번호 뽑아줄수있음
	public int paymentNo();
	//결재문서 데이터 삽입
	public int paymentLogBook(PaymentVO payVO);
	//운행일지 데이터삽입
	public int logDataInsert(PaymentVO payVO);
	//연차계 데이터삽입
	public int offDataInsert(PaymentVO payVO);
	//품의서 데이터삽입
	public int robinDataInsert(PaymentVO payVO);
}
