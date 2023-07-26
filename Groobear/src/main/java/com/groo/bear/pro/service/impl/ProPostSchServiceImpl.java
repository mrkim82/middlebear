package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostSchMapper;
import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.schvo.ProPerCalComVO;
import com.groo.bear.pro.service.schvo.ProPostSchVO;
import com.groo.bear.pro.service.schvo.ProPostWorkSchVO;
import com.groo.bear.pro.service.schvo.ProSchDetailVO;

@Service
public class ProPostSchServiceImpl implements ProPostSchService {
	@Autowired
	ProPostSchMapper ppsm;
	
	@Override
	public List<ProPostSchVO> readSchparti(String id) {
		return ppsm.readSchparti(id);
	}

	@Override
	public int updateSchPartiCheck(ProPostSchVO vo) {
		return ppsm.updateSchPartiCheck(vo);
	}

	@Override
	public List<ProPostSchVO> readPartiList(int proNo) {
		return ppsm.readPartiList(proNo);
	}

	@Override
	public void deletePartiMemberAll(int schNo) {
		ppsm.deletePartiMemberAll(schNo);
	}

	@Override
	public boolean insertPartiMember(ProPostSchVO vo) {
		return ppsm.insertPartiMember(vo);
	}

	@Override
	public int insertPartiMemberAll(List<ProPostSchVO> vo) {
		int result = 0;
		//System.out.println("서비스"+vo);
		
		if(vo.get(0).getId() != null) {
			//전체 삭제
			ppsm.deletePartiMemberAll(vo.get(0).getSchNo());
			
			for (ProPostSchVO proPostSchVO : vo) {
				if(ppsm.insertPartiMember(proPostSchVO)) {
					result++;
				}
			}
			
		} else {
			//전체 삭제
			ppsm.deletePartiMemberAll(vo.get(0).getSchNo());
			result = -1;
		}
		return result;
	}

	@Override
	public List<ProPostWorkSchVO> readWorkSchView(int proNo) {
		return ppsm.readWorkSchView(proNo);
	}

	@Override
	public List<ProPostWorkSchVO> readPersonalSch(String id) {
		return ppsm.readPersonalSch(id);
	}

	@Override
	public List<ProSchDetailVO> readCalDetail(int proNo) {
		return ppsm.readCalDetail(proNo);
	}

	@Override
	public List<ProPostSchVO> readPartiZone(int proNo) {
		return ppsm.readPartiZone(proNo);
	}

	@Override
	public List<ProSchDetailVO> readPerCalDetail(String id) {
		return ppsm.readPerCalDetail(id);
	}

	@Override
	public List<ProPerCalComVO> readPerCalCom(String id) {
		return ppsm.readPerCalCom(id);
	}

	@Override
	public int deleteMemberSchParti(String id) {
		return ppsm.deleteMemberSchParti(id);
	}

}
