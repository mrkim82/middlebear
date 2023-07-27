package com.groo.bear.payment.service;

import java.util.List;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.paging.Criteria;

public interface PaymentService {
	//결재자선택에서 필요한 결재자 사원 리스트정보
	public List<EmpVO> payEmpList();
	//결재문서 페이지
	public int paymentDocForm(PaymentVO payVO);
	//기안자 id,emp_no,emp_name,dept_name 받아오는 거
	public EmpVO payEmpInfo(String id);
	//결재문서 번호
	public int paymentNo();
	//결재문서 데이터삽입
	public int paymentInsert(PaymentVO payVO);
	//개인서명 등록
	public int insertSignImg(EmpVO vo);
	//개인서명 삭제
	public int deleteSignImg(int signNo);
	//개인서명 조회
	public FilesVO searchSignImg(int empNo);
	//운행일지 첨부파일 등록
	public int logInsert(FilesVO vo);
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
	
	
	//결재문서 운행일지 상세조회
	public PaymentVO logList(int payNo);
	//결재문서 연차계 상세조회
	public PaymentVO offList(int payNo);
	//결재문서 품의서 상세조회
	public PaymentVO robinList(int payNo);
}
