package com.groo.bear.payment.mapper;

import java.util.List;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.paging.Criteria;
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
	public int paymentInsert(PaymentVO payVO);
	//운행일지 데이터삽입
	public int logDataInsert(PaymentVO payVO);
	//연차계 데이터삽입
	public int offDataInsert(PaymentVO payVO);
	//품의서 데이터삽입
	public int robinDataInsert(PaymentVO payVO);
	//결재중 문서 전체조회
	public List<PaymentVO> paymentList(Criteria cri,PaymentVO payVO);
	//결재중 문서 페이징용 카운트
	public int countPaymentList(String id);
	//결재완료된 문서 전체조회
	public List<PaymentVO> completePaymentList(Criteria cri,PaymentVO payVO);
	//결재완료된 문서 페이징용 카운트
	public int completePayCount(String id);
	//참조된 문서 전체조회
	public List<PaymentVO> referrerPayList(Criteria cri,PaymentVO payVO);
	//참조된 문서 페이징용 카운트
	public int referrerPayCount(String id);
	//결재문서 운행일지 상세조회
	public PaymentVO logList(int payNo);
	//결재문서 연차계 상세조회
	public PaymentVO offList(int payNo);
	//결재문서 품의서 상세조회
	public PaymentVO robinList(int payNo);
	//결재문서 운행일지 수정
	public int logUpdate(PaymentVO payVO);
	//결재문서 연차계 수정
	public int offUpdate(PaymentVO payVO);
	//결재문서 품의서 수정
	public int robinUpdate(PaymentVO payVO);
	//결재문서 결재자2 결재/반려 업데이트
	public int paymentReject2(PaymentVO payVO);
	//결재문서 결재자3 결재/반려 업데이트
	public int paymentReject3(PaymentVO payVO);
}
