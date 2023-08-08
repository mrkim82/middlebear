package com.groo.bear.pro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProMapper;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.provo.ProAuthVO;
import com.groo.bear.pro.service.provo.ProGroupManageVO;
import com.groo.bear.pro.service.provo.ProGroupVO;
import com.groo.bear.pro.service.provo.ProHideVO;
import com.groo.bear.pro.service.provo.ProPartiAlarmVO;
import com.groo.bear.pro.service.provo.ProPerSettingVO;
import com.groo.bear.pro.service.provo.ProUsersVO;
import com.groo.bear.pro.service.provo.ProVO;

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
		ProUsersVO vo = proMapper.readOrder(id);
		String proPartiFilter = vo.getProPartiFilter();
		String proRange = vo.getProRange();
		return proMapper.readProjectHide(id, proPartiFilter, proRange);
	}
	
	@Override
	public List<ProVO> readProjectStar(String id) {
		ProUsersVO vo = proMapper.readOrder(id);
		String proPartiFilter = vo.getProPartiFilter();
		String proRange = vo.getProRange();
		return proMapper.readProjectStar(id, proPartiFilter, proRange);
	}
	
	@Override
	public int updateStar(String proStarCheck, int proMemNo) {
		if(proStarCheck.equals("Y")) {
			proStarCheck = "N";
		} else {
			proStarCheck = "Y";
		}
		return proMapper.updateStar(proStarCheck, proMemNo);
	}

	@Override
	public List<ProGroupVO> readProjectGroup(String id) {
		return proMapper.readProjectGroup(id);
	}

	@Override
	public List<ProGroupVO> readProjectGroupDetail(int groupNo, String id) {
		ProUsersVO vo = proMapper.readOrder(id);
		System.out.println("vo를찾아서"+vo);
		String proPartiFilter = vo.getProPartiFilter();
		String proRange = vo.getProRange();
		return proMapper.readProjectGroupDetail(groupNo, id, proPartiFilter, proRange);
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
		ProUsersVO vo = proMapper.readOrder(id);
		String proPartiFilter = vo.getProPartiFilter();
		String proRange = vo.getProRange();
		return proMapper.readProjectNoGroup(id, proPartiFilter, proRange);
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
	public int updateProjectFilter(String proPartiFilter, String proRange) {
		return proMapper.updateProjectFilter(proPartiFilter, proRange);
	}

	@Override
	public int updateProjectOrder(String proRange, String id) {
		return proMapper.updateProjectOrder(proRange, id);
	}

	@Override
	public boolean createProGroup(String id) {
		int result = proMapper.createProGroup(id);
		boolean res;
		if(result == 3) {
			res = true;
		} else {
			res = false;
		}
		return res;
	}

	@Override
	public ProAuthVO readProAuth(int proNo) {
		return proMapper.readProAuth(proNo);
	}

	@Override
	public int updateProHide(ProHideVO vo) {
		return proMapper.updateProHide(vo);
	}

	@Override
	public List<ProGroupManageVO> readPerAllPro(String id) {
		return proMapper.readPerAllPro(id);
	}

	@Override
	public List<ProGroupManageVO> readGroupCheckPro(int groupNo, String id) {
		return proMapper.readGroupCheckPro(groupNo, id);
	}

	@Override
	public ProPerSettingVO createGroupProManage(ProGroupManageVO vo) {
		proMapper.createGroupProManage(vo);
		
		return proMapper.readProPerSetting(vo.getProMemNo());
	}

	@Override
	public int deleteGroupProManage(ProGroupManageVO vo) {
		return proMapper.deleteGroupProManage(vo);
	}

	@Override
	public List<ProPartiAlarmVO> readNoPartiPro(String id) {
		return proMapper.readNoPartiPro(id);
	}

	@Override
	public int updateProPartiY(int proMemNo) {
		return proMapper.updateProPartiY(proMemNo);
	}

	@Override
	public int deleteProPartiN(int proMemNo) {
		return proMapper.deleteProPartiN(proMemNo);
	}

	@Override
	public int readNoPartiProCount(String id) {
		return proMapper.readNoPartiProCount(id);
	}

}
