package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.ProPostUserVO;
import com.groo.bear.pro.service.ProPostVO;
import com.groo.bear.pro.service.postvo.ProPostWorkGroupVO;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.postvo.ProPostWritingVO;

public interface ProPostMapper {
	//상단메뉴바
	// 상단메뉴바 조회
	public ProPostVO readTopMenu(int proNo, String id);
	
	// 상단메뉴바 인원수 조회
	public int readTopMenuCount(String id, int proNo);
	
	// 프로젝트 참가자 정보
	public List<ProPostUserVO> readProjectParti(ProPostUserVO vo);
	
	//글 작성
	public void createPostWriting(ProPostWritingVO vo);
	
	//업무 작성
	public void createPostWork(ProPostWorkVO vo);
	//업무 그룹조회
	public List<ProPostWorkGroupVO> readWritingWorkGroup(int proNo);
}
