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
		 		commuteMapper.overWorkEnd(commuteVO);
		 		return commuteMapper.overWorkTime(commuteVO);
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
	public List<CommuteVO> getMyCommuteList(Criteria cri,String id, String monthDate) {
		return commuteMapper.getMyCommute(cri, id, monthDate);
	}


	@Override
	public int settingDay(String id) {
		return commuteMapper.insertDay(id);
	}


	@Override
	public CommuteVO monthWork(String id,String monthDate) {
		return commuteMapper.calWork(id,monthDate);
	}


	@Override
	public int commuteUpdate(CommuteVO commuteVO) {
			commuteMapper.commuteUpdate(commuteVO);
		return commuteMapper.overWorkTime(commuteVO);
	}


	@Override
	public CommuteVO monthNoWork(String id, String monthDate) {
		return commuteMapper.calNoWork(id, monthDate);
	}


	@Override
	public List<CommuteVO> getComInfo(String id) {
		return commuteMapper.comInfo(id);
	}


	@Override
	public int commuteCnt2(String id,String monthDate) {
		return commuteMapper.commuteListCnt2(id, monthDate);
	}


	@Override
	public List<CommuteVO> getAllMonth(Criteria cri, CommuteVO commuteVO, String monthDate) {
		return commuteMapper.calAllWork(cri, monthDate, monthDate);
	}






	
	

	
		
}	
