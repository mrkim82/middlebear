package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostMapper;
import com.groo.bear.pro.mapper.ProTodoNVoteMapper;
import com.groo.bear.pro.service.ProTodoNVoteService;
import com.groo.bear.pro.service.todovote.CreateVoteVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCountVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreListVO;
import com.groo.bear.pro.service.todovote.ProPostTodoCreVO;
import com.groo.bear.pro.service.todovote.ProPostTodoVO;
import com.groo.bear.pro.service.todovote.ProPostVoteVO;
import com.groo.bear.pro.service.todovote.ProTodoDetailVO;
import com.groo.bear.pro.service.todovote.ProTodoPartiCountVO;
import com.groo.bear.pro.service.todovote.ProUpdateTodoVO;
import com.groo.bear.pro.service.todovote.ProUpdateVoteVO;
import com.groo.bear.pro.service.todovote.ProVoteCDVO;

@Service
public class ProTodoNVoteServiceImpl implements ProTodoNVoteService {
	@Autowired
	ProTodoNVoteMapper tv;

	@Autowired
	ProPostMapper ppm;
	
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
		
		//todo용 게시물 생성
		tv.createPostTodo(vo);
		int proPostNo = vo.getProPostNo();
		
		List<ProPostTodoCreListVO> todoDetails = vo.getPptcl();
		
		//todo 생성
		for (ProPostTodoCreListVO todoDetail : todoDetails) {
			todoDetail.setProPostNo(proPostNo);
			tv.createTodo(todoDetail);
			count++;
		}
		
		return count;
	}

	@Override
	public void createPostVote(CreateVoteVO vo) {
		tv.createPostVote(vo);
		
	}

	@Override
	public int updateVoteStatus(int voteNo) {
		return tv.updateVoteStatus(voteNo);
	}

	@Override
	public List<ProTodoPartiCountVO> readVotePartiCount(int proNo) {
		return tv.readVotePartiCount(proNo);
	}

	@Override
	public int insertVote(ProVoteCDVO vo) {
		return tv.insertVote(vo);
	}

	@Override
	public int deleteVote(ProVoteCDVO vo) {
		return tv.deleteVote(vo);
	}

	@Override
	public List<ProVoteCDVO> readxxVote(String id, int proNo) {
		return tv.readxxVote(id, proNo);
	}

	@Override
	public int updateVotePost(ProUpdateVoteVO vo) {
		
		ppm.updateProPostTitle(vo.getPostTitle(), vo.getProPostNo());
		
		tv.updateVote(vo);
		
		if(!vo.getVoteDetailContents().isEmpty()) {
			
			for (int i = 0; i < vo.getVoteDetailContents().size(); i++) {
				tv.createVoteDetail(vo.getVoteDetailContents().get(i), vo.getVoteNo());
			}
		}
		
		
		return 0;
	}

	@Override
	public List<ProTodoDetailVO> readTodoDetail(int proPostNo) {
		return tv.readTodoDetail(proPostNo);
	}

	@Override
	public int deleteTodoDetail(int todoNo) {
		return tv.deleteTodoDetail(todoNo);
	}

	@Override
	public int updateTodo(List<ProUpdateTodoVO> vo) {
		int result = 0;
		
		for (ProUpdateTodoVO proUpdateTodoVO : vo) {
			//제목 변경
			if(proUpdateTodoVO.getPostTitle() != null && !proUpdateTodoVO.getPostTitle().isEmpty()) {
				ppm.updateProPostTitle(proUpdateTodoVO.getPostTitle(), proUpdateTodoVO.getProPostNo());
				result++;
			}
			//항목 변경
			if(proUpdateTodoVO.getTodoContent() != null && proUpdateTodoVO.getTodoNo() != 0) {
				tv.updateTodoDetail(proUpdateTodoVO);
				result++;
			}
			
			//항목 추가
			if(proUpdateTodoVO.getTodoContent() != null && proUpdateTodoVO.getTodoNo() == 0) {
				tv.createTodoDetail(proUpdateTodoVO);
				result++;
			}
			
		}
		
		return result;
	}

	@Override
	public ProUpdateVoteVO readUpVoteDetail(int proPostNo) {
		return tv.readUpVoteDetail(proPostNo);
	}

	
}
