package com.groo.bear.files.mapper;

import java.util.List;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.pro.service.ProFileVO;

public interface FilesMapper {
	//첨부파일 추가
	public int insert(FilesVO vo);
	
	//첨부파일 삭제
	public int delete(String uuid);
	
	
	public List<FilesVO> findByNo(String name, int no); //특정 게시물의 번호로 첨부파일을 찾는 작업
	
	//첨부파일 삭제 처리 -> DB상에서만 삭제가 이루어지는게 아니라 실제 폴더 내의 파일도 같이 삭제 해야 하기 때문에 하는 구문.
	public int deleteAll(String name, int no);
	
	//프로필사진 등록
	public int insertProfImg(EmpVO vo);
	
	//프로필사진 삭제
	public int deleteProfImg(String id);
	
	//넣다가 삭제한 첨부파일 삭제처리
	public List<FilesVO> getOldFiles();
	
	//개인서명 등록
	public int insertSignImg(EmpVO vo);
	
	//개인서명 삭제
	public int deleteSignImg(int signNo);
	
	//개인서명 조회
	public FilesVO searchSignImg(int empNo);
	
	//운행일지 파일등록
	public int logInsert(FilesVO vo);
	
	//전자결재 이미지 조회
	public List<FilesVO> searchPayImg(int payNo);
	
	//전자결재 이미지 제거
	public int deletePayImg(int payNo);
	
	//메일 첨부파일 등록
	public int insertMailFile(FilesVO vo);
	
	//메일 첨부파일 조회
	public FilesVO searchMailFile(int mailNo);
	
	//메일 첨부파일 삭제
	public int deleteMailFile(int mailNo);
	
	//프로젝트
	// 프로젝트 파일관리 테이블 추가
	public int createProFileMan(ProFileVO vo);
	// 프로젝트 파일 조회(게시물)
	public List<ProFileVO> readProFilePost(int proNo);
	//프로젝트 파일 상세 조회
	public List<ProFileVO> readProFilePostDetail(int proPostNo);
	// 파일 업로드(최종)
	public int createProFile(ProFileVO vo);
	
	// 프로젝트 파일 제거
	public int deleteProFile(int proFileNo);
	// 프로젝트 첨부파일 관리 삭제
	public int deleteProFileMan(int proFileNo);
	//개인서명 등록
	public int insertPublicSignImg(EmpVO vo);
}