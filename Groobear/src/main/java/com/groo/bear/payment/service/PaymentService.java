package com.groo.bear.payment.service;

import java.util.List;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.files.domain.FilesVO;

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
	public int paymentLogBook(PaymentVO payVO);
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
}
