package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProTodoNVoteMapper;
import com.groo.bear.pro.service.ProTodoNVoteService;
import com.groo.bear.pro.service.todovote.ProPostTodoVO;
@Service
public class ProTodoNVoteServiceImpl implements ProTodoNVoteService {
	@Autowired
	ProTodoNVoteMapper tv;

	@Override
	public List<ProPostTodoVO> readTodoList(int proNo) {
		return tv.readTodoList(proNo);
	}

	@Override
	public int updateTotoStatus(ProPostTodoVO vo) {
		return tv.updateTotoStatus(vo);
	}
}
