package com.groo.bear.pro.mapper;

import java.util.List;

import com.groo.bear.pro.service.task.ProPostTaskDetailVO;
import com.groo.bear.pro.service.task.ProPostTaskVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkGroupVO;
import com.groo.bear.pro.service.task.ProPostTaskWorkPersonVO;
import com.groo.bear.pro.service.task.ProWorkViewVO;

public interface ProPostTaskMapper {
	//프로젝트(업무) 업무 전체 조회
	public List<ProPostTaskVO> readTaskAllList(int proNo);
	
	//프로젝트(업무) 업무 담당자 조회
	public List<ProPostTaskWorkPersonVO> readTaskWorkPerson(int proNo);
	
	//업무그룹 조회
	public List<ProPostTaskWorkGroupVO> readWorkGroup(int proNo);
	
	//업무단건 조회
	public List<ProPostTaskDetailVO> readWorkDetail(int proNo);
	
	//멤버별 업무 조회 설정
	public ProWorkViewVO readWorkView(int proNo, String id);
	
	//멤버별 업무 조회 변경
	public int updateWorkView(ProWorkViewVO vo);
	
	//회원 탈퇴시 work_group_person 삭제
	public int deleteWorkPerson(String id);
	
	//업무 그룹 생성
	public int createWorkGroup(ProPostTaskWorkGroupVO vo);
	
	//업무 그룹 삭제
	public int deleteWorkGroup(int workGroupNo);
	
	//해당 work 담당자
	public List<String> readDetailWorkPerson(int proPostNo);
}
