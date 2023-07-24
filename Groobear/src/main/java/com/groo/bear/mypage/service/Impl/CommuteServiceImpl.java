package com.groo.bear.mypage.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.mypage.mapper.CommuteMapper;
import com.groo.bear.mypage.service.CommuteService;
import com.groo.bear.mypage.service.CommuteVO;
import com.groo.bear.paging.Criteria;

@Service
public class CommuteServiceImpl implements CommuteService {
	
	@Autowired
	CommuteMapper commuteMapper;



	@Override
	public List<CommuteVO> getAllCommuteList(Criteria cri, CommuteVO commuteVO) {
		return commuteMapper.selectAllCommute(cri, commuteVO);
	}


	@Override
	public int commuteCnt(Criteria cri, CommuteVO commuteVO) {
		return commuteMapper.commuteListCnt(cri, commuteVO);
	}


	@Override
	public int startWork(CommuteVO commuteVO) {
		return commuteMapper.workStart(commuteVO);
	}


	@Override
	public int endWork(CommuteVO commuteVO) {
		return commuteMapper.workEnd(commuteVO);
	}


	@Override
	public int startOverWork(CommuteVO commuteVO) {
		return commuteMapper.overWorkStart(commuteVO);
	}


	@Override
	public int endOverWork(CommuteVO commuteVO) {
		return commuteMapper.overWorkEnd(commuteVO);
	}


	@Override
	public int ewStartOverWork(CommuteVO commuteVO) {
		return commuteMapper.overWorkStartEW(commuteVO);
	}

	@Override
	public CommuteVO chkWork(String id) {
		return commuteMapper.chkWork(id);
	}


	@Override
	public List<CommuteVO> getMyCommuteList(Criteria cri, String id) {
		return commuteMapper.getMyCommute(cri, id);
	}





	
	

	
		
}	
