package com.groo.bear.files.mapper;

import java.util.List;

import com.groo.bear.files.domain.FilesVO;

public interface FilesMapper {
	//첨부파일 추가
	public int insert(FilesVO vo);
	
	//첨부파일 삭제
	public int delete(String uuid);
	
	
	public List<FilesVO> findByBno(int boardNo); //특정 게시물의 번호로 첨부파일을 찾는 작업
	
	//첨부파일 삭제 처리 -> DB상에서만 삭제가 이루어지는게 아니라 실제 폴더 내의 파일도 같이 삭제 해야 하기 때문에 하는 구문.
	public int deleteAll(int boardNo);
	
	//넣다가 삭제한 첨부파일 삭제처리
	public List<FilesVO> getOldFiles();
}