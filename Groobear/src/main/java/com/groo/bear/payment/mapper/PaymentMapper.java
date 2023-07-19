package com.groo.bear.payment.mapper;

import java.util.List;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.payment.service.PaymentVO;

public interface PaymentMapper {
	//결재자선택에서 필요한 결재자 사원 리스트정보
	public List<EmpVO> payEmpList();
	//결재문서 페이지
	public int paymentDoc(PaymentVO payVO);
}
