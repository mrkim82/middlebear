package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProTodoNVoteMapper;
import com.groo.bear.pro.service.ProTodoNVoteService;
import com.groo.bear.pro.service.todovote.ProPostTodoCountVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreListVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreVO;
import com.groo.bear.pro.service.todovote.ProPostTodoVO;
import com.groo.bear.pro.service.todovote.ProPostVoteVO;
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

	@Override
	public List<ProPostTodoCountVO> readAllTodoListPer(int proNo) {
		return tv.readAllTodoListPer(proNo);
	}

	@Override
	public List<ProPostVoteVO> readVoteList(int proNo) {
		return tv.readVoteList(proNo);
	}

	@Override
	public List<ProPostVoteVO> readVoteListCheck(int proNo) {
		return tv.readVoteListCheck(proNo);
	}

	@Override
	public List<ProPostVoteVO> readVoteListParti(int proNo) {
		return tv.readVoteListParti(proNo);
	}

	@Override
	public int createTodo(ProPostTodoCreVO vo) {
		int count = 0;
		//tv.createPostTodo(vo);
		List<ProPostTodoCreListVO> todoDetails = vo.getPptcl();
		System.out.println(todoDetails);
		for (ProPostTodoCreListVO todoDetail : todoDetails) {
			System.out.println("투두" + todoDetail);
			count++;
		}
		
		
		return count;
	}


}
