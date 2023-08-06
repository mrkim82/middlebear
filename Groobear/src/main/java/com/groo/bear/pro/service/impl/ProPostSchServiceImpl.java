package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostMapper;
import com.groo.bear.pro.mapper.ProPostSchMapper;
import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.schvo.ProCreateSchVO;
import com.groo.bear.pro.service.schvo.ProPerCalComVO;
import com.groo.bear.pro.service.schvo.ProPostSchVO;
import com.groo.bear.pro.service.schvo.ProPostWorkSchVO;
import com.groo.bear.pro.service.schvo.ProSchDetailVO;
import com.groo.bear.pro.service.schvo.ProSchUpdateDetailVO;
import com.groo.bear.pro.service.schvo.ProUpdateSchVO;

@Service
public class ProPostSchServiceImpl implements ProPostSchService {
	@Autowired
	ProPostSchMapper ppsm;
	
	@Autowired
	ProPostMapper ppm;
	
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

	@Override
	public void createPostSch(ProCreateSchVO vo) {
		ppsm.createPostSch(vo);
	}

	@Override
	public int updateProSch(ProUpdateSchVO vo) {
		//제목 변경
		ppm.updateProPostTitle(vo.getPostTitle(), vo.getProPostNo());
		
		ppsm.updateProSch(vo);
		return 0;
	}

	@Override
	public ProSchUpdateDetailVO readUpDetailSch(int proPostNo) {
		return ppsm.readUpDetailSch(proPostNo);
	}

}
