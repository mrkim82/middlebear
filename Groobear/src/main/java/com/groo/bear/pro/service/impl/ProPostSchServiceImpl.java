package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostSchMapper;
import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.schvo.ProPostSchVO;
import com.groo.bear.pro.service.schvo.ProPostWorkSchVO;

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
		
		if(vo == null) {
			result = -1;
		} else {
			
			for (ProPostSchVO proPostSchVO : vo) {
				if(ppsm.insertPartiMember(proPostSchVO)) {
					result++;
				}
			}
			
		}
		
		return result;
	}

	@Override
	public List<ProPostWorkSchVO> readWorkSchView(int proNo) {
		return ppsm.readWorkSchView(proNo);
	}

}
