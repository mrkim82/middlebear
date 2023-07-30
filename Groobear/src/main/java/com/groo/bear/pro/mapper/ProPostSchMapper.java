package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.schvo.ProCreateSchVO;
import com.groo.bear.pro.service.schvo.ProPerCalComVO;
import com.groo.bear.pro.service.schvo.ProPostSchVO;
import com.groo.bear.pro.service.schvo.ProPostWorkSchVO;
import com.groo.bear.pro.service.schvo.ProSchDetailVO;
import com.groo.bear.pro.service.schvo.ProUpdateSchVO;

public interface ProPostSchMapper {
	//참석자 조회
	public List<ProPostSchVO> readSchparti(String id);
	
	//참석여부 변경
	public int updateSchPartiCheck(ProPostSchVO vo);
	
	//참석자 현황
	public List<ProPostSchVO> readPartiList(int proNo);
	//참석자 현황
	public List<ProPostSchVO> readPartiZone(int proNo);
	
	// 참석자 전체 삭제
	public void deletePartiMemberAll(int schNo);
	
	//참석자 추가
	public boolean insertPartiMember(ProPostSchVO vo);
	
	//캘린더 화면 조회
	public List<ProPostWorkSchVO> readWorkSchView(int proNo);
	//캘린더 단건 조회
	public List<ProSchDetailVO> readCalDetail(int proNo);
	
	//개인 캘린더
	// 캘린더 화면 조회
	public List<ProPostWorkSchVO> readPersonalSch(String id);
	// 개인 캘린더 상세 조회
	public List<ProSchDetailVO> readPerCalDetail(String id);
	// 개인 캘린더 댓글 조회
	public List<ProPerCalComVO> readPerCalCom(String id);
	
	//회원 탈퇴용 참가자 삭제
	public int deleteMemberSchParti(String id);
	
	//캘린더 게시글 등록
	public void createPostSch(ProCreateSchVO vo);
	
	//스케쥴 수정
	public int updateProSch(ProUpdateSchVO vo);
	
}
