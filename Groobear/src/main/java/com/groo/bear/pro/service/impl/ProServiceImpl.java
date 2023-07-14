package com.groo.bear.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProMapper;
import com.groo.bear.pro.service.ProGroupVO;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProUsersVO;
import com.groo.bear.pro.service.ProVO;

@Service
public class ProServiceImpl implements ProService {
	@Autowired
	ProMapper proMapper;

	@Override
	public void insertPro(Map<String, Object> map) {
		proMapper.createPro(map);
	}

	@Override
	public List<ProVO> readProject(Map<String, Object> map) {
		return proMapper.readProject(map);
	}

	@Override
	public List<ProVO> readProjectHide(String id) {
		return proMapper.readProjectHide(id);
	}
	
	@Override
	public List<ProVO> readProjectStar(String id) {
		return proMapper.readProjectStar(id);
	}
	
	@Override
	public int updateStarY(int pMN) {
		return proMapper.updateStarY(pMN);
	}

	@Override
	public int updateStarN(int pMN) {
		return proMapper.updateStarN(pMN);
	}

	@Override
	public List<ProGroupVO> readProjectGroup(String id) {
		return proMapper.readProjectGroup(id);
	}

	@Override
	public List<ProGroupVO> readProjectGroupDetail(int groupNo, String id) {
		return proMapper.readProjectGroupDetail(groupNo, id);
	}

	@Override
	public int createProjectGroup(String groupName, String id) {
		return proMapper.createProjectGroup(groupName, id);
	}

	@Override
	public List<ProVO> readProjectParti(String id) {
		return proMapper.readProjectParti(id);
	}

	@Override
	public List<ProVO> readProjectNoGroup(String id) {
		return proMapper.readProjectNoGroup(id);
	}

	@Override
	public int readProjectGroupNo() {
		return proMapper.readProjectGroupNo();
	}

	@Override
	public int updateGroupName(ProGroupVO vo) {
		return proMapper.updateGroupName(vo);
	}

	@Override
	public int deleteGroup(int groupNo) {
		return proMapper.deleteGroup(groupNo);
	}

	@Override
	public ProUsersVO readOrder(String id) {
		return proMapper.readOrder(id);
	}

	@Override
	public int updateProjectFilter(String proRange) {
		return proMapper.updateProjectFilter(proRange);
	}

	@Override
	public int updateProjectOrder(String proRange, String id) {
		return proMapper.updateProjectOrder(proRange, id);
	}




}
